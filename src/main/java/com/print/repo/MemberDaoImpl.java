package com.print.repo;

import java.util.List;

import com.print.domain.Member;


//@Repository
//@Transactional
public class MemberDaoImpl implements MemberDao
{
//    @Autowired
//    private EntityManager em;

    public Member findById(Long id)
    {
//        return em.find(Member.class, id);
        return null;
    }

    public Member findByEmail(String email)
    {
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
//        Root<Member> member = criteria.from(Member.class);
//
//        /*
//         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
//         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
//         */
//
//        criteria.select(member).where(builder.equal(member.get("email"), email));
//        return em.createQuery(criteria).getSingleResult();
    	return null;
    }

    public List<Member> findAllOrderedByName()
    {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
//        Root<Member> member = criteria.from(Member.class);
//
//        /*
//         * Swap criteria statements if you would like to try out type-safe criteria queries, a new
//         * feature in JPA 2.0 criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
//         */
//
//        criteria.select(member).orderBy(cb.asc(member.get("name")));
//        return em.createQuery(criteria).getResultList();
        return null;
    }

    public void register(Member member)
    {
//        em.persist(member);
        return;
    }
}
