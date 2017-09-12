package dbService.dao;

import dbService.dataSets.UsersDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UsersDataSet get(long id) throws HibernateException {
        return (UsersDataSet) session.get(UsersDataSet.class, id);
    }

    public long getUserId(String login) throws HibernateException {
        try {
            Criteria criteria = session.createCriteria(UsersDataSet.class);
            Long id = ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
//       return ((UsersDataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getId();
            return id;
        } catch (NullPointerException e) {
            return 0;
        }
    }

    public long insertUser(String login) throws HibernateException {
        return (Long) session.save(new UsersDataSet(login));
    }
}
