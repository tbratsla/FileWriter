package com.seeton.filewriter.service;

import com.seeton.filewriter.entity.PersonnelDataEntity;
import org.hibernate.*;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.Instant;

import static com.seeton.filewriter.entity.PersonnelDataEntity.PERSONNEL_REF_QUERY_NAME;
import static org.hibernate.cfg.AvailableSettings.JPA_SHARED_CACHE_RETRIEVE_MODE;
import static org.hibernate.cfg.AvailableSettings.JPA_SHARED_CACHE_STORE_MODE;

@Service
public class Generator {

    @PersistenceContext
    private EntityManager em;
    private SessionFactory sessionFactory;

    @PostConstruct
    public void initSessionFactory() {
        Session session = em.unwrap(Session.class);
        sessionFactory = session.getSessionFactory();
    }

    @Transactional
    public void generate(Instant DateFrom, Instant DateTo) {
        try (
                StatelessSession statelessSession = sessionFactory.openStatelessSession()
        ) {
            ReportExcelStreamWriter writer = new ReportExcelStreamWriter();
            Query<PersonnelDataEntity> query = statelessSession.createNamedQuery(PERSONNEL_REF_QUERY_NAME, PersonnelDataEntity.class);
            query.setParameter(1, DateFrom);
            query.setParameter(2, DateTo);
            query.setHint(JPA_SHARED_CACHE_STORE_MODE, null);
            query.setHint(JPA_SHARED_CACHE_RETRIEVE_MODE, null);
            ScrollableResults results = query.scroll(ScrollMode.FORWARD_ONLY);
            int index = 0;
            while (results.next()) {
                index++;
                writer.createRow(index, (PersonnelDataEntity)results.get(0));
            }
            writer.writeWorkbook();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
