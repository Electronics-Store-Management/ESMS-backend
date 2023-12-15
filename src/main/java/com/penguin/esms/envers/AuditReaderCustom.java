package com.penguin.esms.envers;

import org.hibernate.HibernateException;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.CrossTypeRevisionChangesReader;
import org.hibernate.envers.exception.AuditException;
import org.hibernate.envers.exception.NotAuditedException;
import org.hibernate.envers.exception.RevisionDoesNotExistException;
import org.hibernate.envers.query.AuditQueryCreator;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AuditReaderCustom implements AuditReader {
    @Override
    public <T> T find(Class<T> aClass, Object o, Number number) throws IllegalArgumentException, NotAuditedException, IllegalStateException {
        return null;
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, Date date) throws IllegalArgumentException, NotAuditedException, RevisionDoesNotExistException, IllegalStateException {
        return null;
    }

    @Override
    public <T> T find(Class<T> aClass, Object o, LocalDateTime localDateTime) throws IllegalArgumentException, NotAuditedException, RevisionDoesNotExistException, IllegalStateException {
        return null;
    }

    @Override
    public <T> T find(Class<T> aClass, String s, Object o, Number number) throws IllegalArgumentException, NotAuditedException, IllegalStateException {
        return null;
    }

    @Override
    public <T> T find(Class<T> aClass, String s, Object o, Number number, boolean b) throws IllegalArgumentException, NotAuditedException, IllegalStateException {
        return null;
    }

    @Override
    public List<Number> getRevisions(Class<?> aClass, Object o) throws IllegalArgumentException, NotAuditedException, IllegalStateException {
        return null;
    }

    @Override
    public List<Number> getRevisions(Class<?> aClass, String s, Object o) throws IllegalArgumentException, NotAuditedException, IllegalStateException {
        return null;
    }

    @Override
    public Date getRevisionDate(Number number) throws IllegalArgumentException, RevisionDoesNotExistException, IllegalStateException {
        return null;
    }

    @Override
    public Number getRevisionNumberForDate(Date date) throws IllegalStateException, RevisionDoesNotExistException, IllegalArgumentException {
        return null;
    }

    @Override
    public Number getRevisionNumberForDate(LocalDateTime localDateTime) throws IllegalStateException, RevisionDoesNotExistException, IllegalArgumentException {
        return null;
    }

    @Override
    public <T> T findRevision(Class<T> aClass, Number number) throws IllegalArgumentException, RevisionDoesNotExistException, IllegalStateException {
        return null;
    }

    @Override
    public <T> Map<Number, T> findRevisions(Class<T> aClass, Set<Number> set) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public <T> T getCurrentRevision(Class<T> aClass, boolean b) {
        return null;
    }

    @Override
    public AuditQueryCreator createQuery() {
        return null;
    }

    @Override
    public boolean isEntityClassAudited(Class<?> aClass) {
        return false;
    }

    @Override
    public boolean isEntityNameAudited(String s) {
        return false;
    }

    @Override
    public String getEntityName(Object o, Number number, Object o1) throws HibernateException {
        return null;
    }

    @Override
    public CrossTypeRevisionChangesReader getCrossTypeRevisionChangesReader() throws AuditException {
        return null;
    }
}
