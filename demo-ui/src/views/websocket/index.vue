<template>
  <div class="app-container">
    <el-row :gutter="10">
      <el-col :span="12">
        <el-input v-model="username"></el-input>
        <el-button type="primary" @click="connectWs(username)">连接</el-button>
        <el-button type="primary" @click="closeWs">关闭</el-button>
        <div v-for="(item,index) in message" :key="index">
          <p v-text="item"></p>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {closeWebsocket, connectWebsocket} from "@/utils/websocket";

export default {
  name: "index",
  data() {
    return {
      wsUri: null,
      username: null,
      message: [],
      wsData: {
        message: "01010100"
      }
    }
  },
  created() {
    // connectWebsocket("ws://localhost:8086/websocket/admin",this.wsData,this.wsSuccess,this.wsError);
  },
  beforeDestroy() {
    closeWebsocket();
  },
  methods: {
    wsSuccess(data) {
      this.message.push(data);
    },
    wsError() {
      this.message.push("---error---");
    },
    // 连接ws
    connectWs(user) {
      connectWebsocket("ws://localhost:8086/websocket/" + user, this.wsData, this.wsSuccess, this.wsError);
    },
    // 关闭ws
    closeWs() {
      closeWebsocket();
      this.message = [];
    }
  }
}
</script>

<style scoped>

</style>