<template>

  <IncidentManagement msg="事件管理系统"/>
</template>

<script>
import IncidentManagement from './components/IncidentManagement.vue'

export default {
  name: 'App',
  components: {
    IncidentManagement
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
