# incident-management
## 事件管理系统
### 简介
`这是一个事件管理系统，该系统支持事件列表查询，事件新增，事件修改以及事件删除`

###技术架构
  三层分层架构：系统分三层进行设计,包括表示层、业务逻辑层、数据访问层。
  
  `表现层： 接收用户请求，调用业务逻辑层处理业务逻辑，返回处理结果。
  
  业务逻辑层：要负责对数据层的操作，即把数据层的操作进行组合处理。
  
  数据访问层：主要负责对非原始数据（数据库或者文本文件等存放数据的形式）的操作，而不是指原始数据。
  也就是说，是对数据库的操作，而不是数据。具体为业务逻辑层或表示层提供数据服务。
  `

  技术框架：Springboot，JAVA17, H2, React
  
  接口设计:所有接口采用RESTful风格进行设计，通过统一的接口和标准的HTTP方法来实现客户端与服务器之间的高效、可靠、可扩展的交互。
  
  `
    查询事件列表
	
	接口类型：GET，
	url:incident/getList
	接口参数：无参
	
  
    新增事件
	接口类型：PUT,
	url:incident/create
	接口参数：{"incidentName":"","incidentContent":""}
	
    修改事件
	接口类型：PUT
	
	url:incident/modify
	接口参数：{"incidentName":"","incidentContent":""，"id":""}

    删除事件
	接口类型： DELETE
	URL:incident/delete/{id}
	接口参数：无参
    	
	`
  
  数据库设计：
  id Long primarykey; 'id'
  incident_name varchar2(50); comment '事件名称'
  incident_content  varchar2(200); comment '事件内容'
  created_by  varchar2(50); comment '创建人'
  create_date datetime;  comment '创建时间'
  updated_by varchar2(50);  comment '更新人'
  last_update datetime;  comment '更新时间'
  
  
  测试设计：
  
  使用junit实现单元测试
  
  测试案例：
  `新增：1.新增一个不存在的事件；2.新增一个存在的事件名称。
  
  变更：1.只变更内容；2.变更名称不与其他事件名称冲突；3.变更名称且与其他事件名称冲突；
  
  删除：删除一个事件。
  
  列表：1.未新增数据时查询，2.事件变更后查询。`
  
  部署：线上部署可以使用docker部署，新建DockerFile文件，基于镜像源，将构建包放到镜像部署目录下，创建新镜像，直接启动镜像。
  
   
  
  
