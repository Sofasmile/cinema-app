package cinema.dao.impl;

import java.util.Optional;

import cinema.dao.AbstractDao;
import cinema.model.RoleName;
import cinema.dao.RoleDao;
import cinema.exception.DataProcessingException;
import cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(String roleName) {
        try (Session session = factory.openSession()) {
            return session.createQuery("from Role r where r.roleName = :role", Role.class)
                    .setParameter("role", RoleName.valueOf(roleName))
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Not found role " + roleName + " ", e);
        }
    }
}
