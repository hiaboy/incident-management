# incident-management
## 事件管理系统
### 简介
	这是一个事件管理系统，该系统支持事件列表查询，事件新增，事件修改以及事件删除

### 技术架构
####  三层分层架构：系统分三层进行设计,包括表示层、业务逻辑层、数据访问层。
  
	  表现层： 接收用户请求，调用业务逻辑层处理业务逻辑，返回处理结果。
	  
	  业务逻辑层：要负责对数据层的操作，即把数据层的操作进行组合处理。
	  
	  数据访问层：主要负责对非原始数据（数据库或者文本文件等存放数据的形式）的操作，而不是指原始数据。
	  也就是说，是对数据库的操作，而不是数据。具体为业务逻辑层或表示层提供数据服务。

####  技术框架：
#####  技术选型： Springboot，JAVA17, H2
  
#####  接口设计: 所有接口采用RESTful风格进行设计，通过统一的接口和标准的HTTP方法来实现客户端与服务器之间的高效、可靠、可扩展的交互。
  
  
    查询事件列表
	
	接口类型：GET，
	url:incident/getList
	请求参数：无参
        返回参数：{
		    "list": [
		        {
		            "id": 2,
		            "incidentName": "",
		            "incidentContent": "",
		            "createdBy": "",
		            "createDate": "",
		            "updatedBy": "",
		            "lastUpdate": ""
		        }
		    ],
		    "status": 200
		}
	
  
    新增事件
	接口类型：PUT,
	url:incident/create
	请求参数：{"incidentName":"","incidentContent":""}
        返回参数：{
		    "message": "创建成功！",
		    "status": 200
		  }
	
    修改事件
	接口类型：PUT
	url:incident/modify
	请求参数：{"incidentName":"","incidentContent":""，"id":""}
        返回参数：{
		    "message": "更新成功！",
		    "status": 200
		  }
	

    删除事件
	接口类型： DELETE
	URL:incident/delete/{id}
	请求参数：无参
        返回参数：{
		    "message": "删除成功！",
		    "status": 200
		  }
    	
  
  数据库设计：
  
	  id Long primarykey; comment 'id'
	  
	  incident_name varchar2(50); comment '事件名称'
	  
	  incident_content  varchar2(200); comment '事件内容'
	  
	  created_by  varchar2(50); comment '创建人'
	  
	  create_date datetime;  comment '创建时间'
	  
	  updated_by varchar2(50);  comment '更新人'
	  
	  last_update datetime;  comment '更新时间'
  
  
  
###  测试设计：

#### 单元测试
  
  使用junit实现单元测试
  
####  测试案例：
  `
  
      可以按images目录下的操作录屏操作如下测试案例
	  新增：1.在新增输入框输入不存在的事件名，点击CREATE；2.在新增输入框输入存在的事件名，点击CREATE。
	  
	  变更：前提，在要变更的事件栏处点击EDIT，编辑框内容会更新为对应的数据，然后进行如下操作；1.只变更内容,点击MODIFY；
	  2.变更名称不与其他事件名称冲突,点击MODIFY；3.变更名称且与其他事件名称冲突,点击MODIFY；
	  
	  删除：在要删除的事件栏后点击DELETE。
	  
	  列表：1.未新增数据时点击QUERY，2.事件变更后会自动查询。
  `

### 部署及发布

      本地运行
	    incident-management-api 服务可以在idea环境下运行
		incident-app 服务需要在命令行cd到该目录下后，执行 npm run serve 命令，运行成功后，在浏览器输入http://localhost:8080/即可进入系统页面
  
	  服务器部署
	  本系统可以使用docker部署，新建DockerFile文件，基于镜像源，将构建包放到镜像部署目录下，创建新镜像，然后启动镜像,下面是DockerFile镜像内容。

          FROM openjdk:17-jdk-alpine
 
	  WORKDIR /app
		
	  COPY target/incident-management.jar /app/incident-management.jar
		
	  EXPOSE 8080
		
	  ENTRYPOINT ["java", "-jar", "/app/incident-management.jar"]

   
   DockerFile文件创建完成后，执行下面命令生成镜像
   
          docker build -t incident-management:1.0.0 .
	  镜像创建成功执行下面命令启动镜像
          docker run -d -p 8080:8080 --name incident-management incident-management:1.0.0



  
   
  
  
