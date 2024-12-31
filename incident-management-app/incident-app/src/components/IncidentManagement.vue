<template>
  <div>
    <h1>{{ msg }}</h1>
    <div >
      <el-form>
        <el-form-item>
          <el-button @click = "getList()" label = "QUERY">QUERY</el-button>
        </el-form-item>
      </el-form>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="incidentName" label="incidentName" width="180"></el-table-column>
      <el-table-column prop="incidentContent" label="incidentContent" width="180"></el-table-column>
      <el-table-column prop="createdBy" label="createdBy"></el-table-column>
      <el-table-column prop="createDate" label="createDate" ></el-table-column>
      <el-table-column prop="updatedBy" label="updatedBy" ></el-table-column>
      <el-table-column prop="lastUpdate" label="lastUpdate" ></el-table-column>
      <el-table-column label = "DELTTE" >
        <template #default="scope">
          <el-button @click="del(scope.row.id)">DELTTE</el-button>
        </template>
      </el-table-column>
      <el-table-column label = "EDIT" >
        <template #default="scope">
          <el-button @click="edit(scope.row)">EDIT</el-button>
        </template>
      </el-table-column>
    </el-table>

      <div class="inline-form">

        <el-form label-width="120px" inline>

          <el-form-item    label="incidentName">
            <el-input v-model="incident.name" class="inline-input"></el-input>
          </el-form-item>
          <el-form-item  label="incidentContent">
            <el-input v-model="incident.content" class="inline-input"></el-input>
          </el-form-item>

          <el-form-item  >
            <el-button @click="create" label="CREATE">CREATE</el-button>
          </el-form-item>

        </el-form>

      </div>

      <div class="inline-form">

        <el-form label-width="120px" inline>

          <el-form-item    label="incidentName">
            <el-input v-model="editIncident.name"  class="inline-input"></el-input>
          </el-form-item>
          <el-form-item  label="incidentContent">
            <el-input v-model="editIncident.content"  class="inline-input"></el-input>
          </el-form-item>

          <el-form-item  >
            <el-button @click="modify" label="MODIFY">MODIFY</el-button>
          </el-form-item>

        </el-form>

      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive, onMounted, inject  } from 'vue';
import {ElTable, ElTableColumn   } from "element-plus";

export default {
  name: 'IncidentManagement',
  props: {
    msg: String
  },
  components: {
    ElTable,
    ElTableColumn
  },
  setup(){
    const tableData = ref([]);
    const incident = reactive({'name': null, "content" : null, "id": null});
    const editIncident = reactive({'name': null, "content" : null, "id": null});
    const axios = inject('axios');
    const getList = async() =>{
      try {
          const response = await axios.get('/incident/getList');
        tableData.value = response.data.list;
      } catch (error) {
        console.error('获取事件列表失败:', error);
      }
    }

    // 创建新项目
    const create = async () => {
      try {
        const response = await axios.put('/incident/create', { incidentName: incident.name ,
          incidentContent:incident.content });
        if(response.data.status === 200){
          getList();
        }else if(response.data.status === -1){
          alert(response.data.message);
        }else {
          alert('创建事件失败:');
        }

      } catch (error) {
        alert('创建事件失败:');
        console.error('创建事件失败:', error);
      }
    };

    // 更新新项目
    const modify = async () => {
      try {
        const response = await axios.put('/incident/modify', { incidentName: editIncident.name ,
          incidentContent:editIncident.content, id: editIncident.id});
        if(response.data.status === 200){
          await getList();
        }else if(response.data.status === -1){
          alert(response.data.message);
        }else {
          alert('更新事件失败:');
        }
      } catch (error) {
        alert('更新事件失败:');
        console.error('更新事件失败:', error);
      }
    };

    // 删除新项目
    const del = async (id) => {
      try {
        const response = await axios.delete('/incident/delete/' + id);
        if(response.data.status === 200){
          getList();
        }else if(response.data.status === -1){
          alert(response.data.message);
        }else {
          alert('删除事件失败:');
        }
      } catch (error) {
        console.error('删除事件失败:', error);
      }
    };

    const edit = async (item) => {

      editIncident.id = item.id;
      editIncident.content = item.incidentContent;
      editIncident.name = item.incidentName;

      console.log(item);
      console.log(editIncident);

    }



    const cancelEdit = async () => {
      editIncident.value = {'name': null, "content" : null};
    }

    onMounted(() => {
      getList();
    });

    return{
      tableData,
      incident,
      editIncident,
      getList,
      create,
      modify,
      del,
      edit,
      cancelEdit
    }
  }


}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
.inline-form {
  display: flex;
  align-items: center; /* 垂直居中 */
}

.inline-form .el-form-item {
  margin-right: 20px; /* 表单项之间的间距 */
  flex: 1; /* 让表单项根据内容自适应宽度 */
}

.inline-form .el-form-item:last-child {
  margin-right: 0; /* 最后一个表单项不需要右边距 */
}

.inline-input {
  width: 100%; /* 输入框宽度自适应 */
  box-sizing: border-box; /* 包括边框和内边距在内计算宽度 */
}
</style>
