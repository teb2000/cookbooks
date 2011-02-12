package no.nuppers.repository.support;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-rep-config.xml"})
@TransactionConfiguration(transactionManager = "rep.transactionManager", defaultRollback = true)
@Transactional()
public abstract class AbstractRepositoryTest {
	
	@PersistenceContext
	protected EntityManager entityManager;

}
