package cn.edu.qfnu.demo.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;

@Aspect
@Configuration
public class TransactionAdviceConfig {
    /**
     * 事务管理器
     */
    private final PlatformTransactionManager transactionManager;

    public TransactionAdviceConfig(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Bean
    public TransactionInterceptor txAdvice(){

        RuleBasedTransactionAttribute txAttr_required = new RuleBasedTransactionAttribute();
        txAttr_required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));

        RuleBasedTransactionAttribute txAttr_readOnly = new RuleBasedTransactionAttribute();
        txAttr_readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        txAttr_readOnly.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        txAttr_readOnly.setReadOnly(true);

        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("find*", txAttr_readOnly);
        source.addTransactionalMethod("save*",txAttr_required);
        source.addTransactionalMethod("remove*", txAttr_required);

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public Advisor txAdviceAdvisor(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        // cn.edu.qfnu.demo.service
        // cn.edu.qfnu.demo.test.service
        // cn.edu.qfnu.demo.test.test.service
        pointcut.setExpression("execution (* cn.edu.qfnu.demo.***.service.*.*(..))");
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
