package study.daydayup.wolf.business.uc.api.crm.staff.service.info;

import study.daydayup.wolf.business.uc.api.crm.staff.dto.CompanyDTO;
import study.daydayup.wolf.framework.rpc.Result;
import study.daydayup.wolf.framework.rpc.page.Page;
import study.daydayup.wolf.framework.rpc.page.PageRequest;

/**
 * study.daydayup.wolf.business.uc.api.crm.staff.service
 *
 * @author Wingle
 * @since 2020/4/22 9:20 下午
 **/
public interface AdminService {
    Result<Page<CompanyDTO>> findCompanies(Long accountId, PageRequest pageRequest);
}
