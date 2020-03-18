package study.daydayup.wolf.business.org.api.task.dto.request.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import study.daydayup.wolf.business.org.api.task.dto.TaskOwner;
import study.daydayup.wolf.framework.layer.api.Request;

/**
 * study.daydayup.wolf.business.org.api.task.dto.request.task
 *
 * @author Wingle
 * @since 2020/3/16 12:27 上午
 **/
@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class TaskTypeRequest extends TaskOwner implements Request {
    private Integer state;
    private Integer taskType;
    private Integer projectId;
}
