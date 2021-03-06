package study.daydayup.wolf.business.union.deploy.dubbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import study.daydayup.wolf.framework.util.ContextUtil;

/**
 * study.daydayup.wolf.business.account.deploy.web
 *
 * @author Wingle
 * @since 2019/9/29 2:13 PM
 **/
@SpringBootApplication(scanBasePackages = {"study.daydayup.wolf"})
@MapperScan({
         "study.daydayup.wolf.business.account.biz.dal.dao"
        , "study.daydayup.wolf.business.uc.crm.biz.customer.credit.dal.dao"
        , "study.daydayup.wolf.business.uc.crm.biz.customer.info.dal.dao"
        , "study.daydayup.wolf.business.uc.crm.biz.staff.info.dal.dao"
        , "study.daydayup.wolf.business.uc.setting.biz.dal.dao"
        , "study.daydayup.wolf.business.goods.biz.dal.dao"
        , "study.daydayup.wolf.business.org.biz.task.dal.dao"
        , "study.daydayup.wolf.business.trade.order.biz.dal.dao"
        , "study.daydayup.wolf.business.pay.biz.dal.dao"
        , "study.daydayup.wolf.bigdata.datav.biz.dal.dao"
})
@EnableDiscoveryClient
public class DubboUnionApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DubboUnionApplication.class, args);

        ContextUtil.init(context);
    }
}
