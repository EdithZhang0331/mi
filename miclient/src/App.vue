<script setup>
import axios from 'axios';
import { provide } from 'vue';

provide("axios", axios)
axios.defaults.baseURL = "http://localhost:7002/"
</script>


<template>
  <router-view></router-view>
</template>


<!-- scoped标识局部样式 -->
<style>
html,body,div,span,h1,h2,h3,h4,h5,h6,ul,li,p{
	margin: 0;
	padding: 0;
}
html,body,#app{
	width: 100%;
	height: 100%;
	font-family: Helvetica Neue,Helvetica,Arial,Microsoft Yahei,Hiragino Sans GB,Heiti SC,WenQuanYi Micro Hei,sans-serif;
}
ul{
	list-style: none;
}
a{
	text-decoration: none;
}
</style>