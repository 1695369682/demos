import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activitiTest {
    /**
     * 启动activity
     */
    @Test
    public void ActivityInit() {
        //获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }
    /**
     * 部署流程--将bpmn图给activiti
     */
    @Test
    public void Activity() {
        //获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取仓库Service RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //调用service的api--部署对象
        Deployment deployment = repositoryService.createDeployment().
                addClasspathResource("bpmn/leaveProcessclaim.bpmn20.xml").
//                addClasspathResource("bpmn/leaveDown.png").
                deploy();
        System.out.println(deployment);
    }
    /**
     * 删除流程定义
     */
    @Test
    public void testDel(){
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        //部署id
        String deploymentId="30001";
        //删除,如果该流程定义已有流程实例启动则删除时出错
        repositoryService.deleteDeployment(deploymentId);
        //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设置为false非级别删除方式，如果流程
        repositoryService.deleteDeployment(deploymentId,true);
    }
    /**
     *    启动流程
     */
    @Test
    public void ActivityRun() {
        //获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取仓库Service RepositoryService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String bussinessKey="zhj";
        //根据流程图定义的key启动流程实例（bpmn的文件名）
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveProcessclaim",bussinessKey);


        System.out.println("流程实例ID：:" + processInstance.getProcessInstanceId());
        System.out.println("获取ID：：" + processInstance.getId());
        System.out.println("获取部署 ID" + processInstance.getDeploymentId());
        System.out.println("对应的流程定义ID" + processInstance.getProcessDefinitionId());
    }
    /**
     * 任务查询
     */
    @Test
    public void testTask(){
        //获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取仓库Service RepositoryService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //TaskService
        TaskService taskService=processEngine.getTaskService();

        List<Task> tasks = taskService.createTaskQuery()
                        .processDefinitionKey("leaveDown2")
                                .list();
        for (Task task : tasks) {
            System.out.println("流程实例Id:"+task.getProcessInstanceId());
            System.out.println("流程定义id:"+task.getProcessDefinitionId());
            System.out.println("taskId:"+task.getId());
            //根据流程实例id获取流程实例
            ProcessInstance processInstance=runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(task.getProcessInstanceId())
                    .singleResult();
            //通过流程实例获取bussinesskey
            System.out.println("bussinesskey"+processInstance.getBusinessKey());
        }
    }
    /**
     * 执行任务
      */
    @Test
    public void testcompleteTask(){
        // 获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取任务 Service  TaskService
        TaskService taskService = processEngine.getTaskService();
        // 完成任务
        String taskId = "42502";
        taskService.complete(taskId);
    }
    /**
     * 查询流程定义
     */
    @Test
    public void test(){
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();

        List<ProcessDefinition> processes=repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("leaveDown")
                .list();
        for (ProcessDefinition process : processes) {
            System.out.println(process.getDeploymentId());
            System.out.println(process.getName());
            System.out.println(process.getDiagramResourceName());
        }
    }
    /**
     * 资源下载
     * @throws Exception
     */
    @Test
    public void testDownloadResource() throws Exception {
        //创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取仓库服务
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //获取流程定义对象
        List<ProcessDefinition> definitions = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey("leaveDown")
                .list();
        ProcessDefinition definition=definitions.get(1);
        //获取部署ID
        String deploymentId = definition.getDeploymentId();
        String bpmnName=definition.getResourceName();
        String PngName=definition.getDiagramResourceName();
        System.out.println(bpmnName);
        System.out.println(PngName);
        //获取bpmn的输入流
        InputStream bpmnInput = repositoryService.getResourceAsStream(
                deploymentId,
                bpmnName);
        System.out.println(bpmnInput);
        //获取png的输入流
        InputStream pngInput = repositoryService.getResourceAsStream(
                deploymentId,
                PngName);
        //设置bpmn输入
        FileOutputStream bpmnOutPut = new FileOutputStream("E:/leave.bpmn");
//        //设置png输入
        FileOutputStream pngOutPut = new FileOutputStream("E:/leave.png");
        IOUtils.copy(bpmnInput,bpmnOutPut);
        IOUtils.copy(pngInput,pngOutPut);

        bpmnInput.close();
        bpmnOutPut.close();
    }

    /**
     * 挂起流程定义
     */
    @Test
    public void testSuspendAllProcessInstance(){
        //创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //获取流程定义对象
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionKey("leaveDown2")
                .latestVersion() // 同名就拿最新的版本
                .singleResult();
        if (processDefinition.isSuspended())
        {
            System.out.println("被挂起了");
        }else {
            System.out.println("没挂起");
            repositoryService.suspendProcessDefinitionById(processDefinition.getId());
        }
    }
    /**
     * 挂起流程实例
     */
    @Test
    public void testSuspendSingleProcessInstance(){
        //流程实例Id
        String processInstanceId = "30001";
        //创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取RepositoryService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //根据流程实例Id获取流程实例对象
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)//根据id获取流程实例
                .singleResult();
        if (processInstance.isSuspended()){
            System.out.println("挂起了");
        }else {
            System.out.println("没挂起，我给挂上");
            runtimeService.suspendProcessInstanceById(processInstance.getProcessInstanceId());
        }
    }
    /**
     *    启动流程--绑定负责人
     */
    @Test
    public void ActivityRun1() {
        //获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取仓库Service RepositoryService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //bussinesskey
        String bussinesskey = "zhj";
        //定义传递的负责人
        Map<String,Object> variables  = new HashMap<String, Object>();
        variables.put("assignee0","zhangsan");
        variables.put("assignee1","lisi");
        //根据流程图定义的key启动流程实例（bpmn的文件名）
        ProcessInstance processInstance = runtimeService.
                startProcessInstanceByKey("leaveProcessAssignee",bussinesskey,variables);

    }
    /**
     * 任务查询--候选人
     */
    @Test
    public void testTask1(){
        //获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //TaskService
        TaskService taskService=processEngine.getTaskService();

        Task tasks = taskService.createTaskQuery()
                .processDefinitionKey("leaveProcessclaim")
                .processInstanceId("55001")
                .taskCandidateUser("zhangsan")
                .singleResult();
        System.out.println("任务id"+tasks.getId());
    }

    /**
     * 候选人领取任务
     */
    @Test
    public void testClaimTask(){
        //任务ID
        String taskId = "55005";
        String assignee = "zhangsan";
        //创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获取TaskService
        TaskService taskService = processEngine.getTaskService();
        //领取任务
        taskService.claim(taskId,assignee);
    }
}
