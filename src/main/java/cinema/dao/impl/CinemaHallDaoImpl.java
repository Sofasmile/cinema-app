package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.model.CinemaHall;
import cinema.dao.CinemaHallDao;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDao<CinemaHall> implements CinemaHallDao {
    public CinemaHallDaoImpl(SessionFactory factory) {
        super(factory, CinemaHall.class);
    }
}
