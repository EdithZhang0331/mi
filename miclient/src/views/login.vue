<script setup>
import Footer from '../components/footer.vue';
import axios from 'axios';
import { setSessionStorage } from '../common';
import { useRouter } from 'vue-router';
import { ref } from 'vue';
const telId = ref('')
const password = ref('')
const router = useRouter()
function login(){
    axios.get("login?telId=" + telId.value + "&password=" + password.value).then(function(response){
        let customer = response.data
        if(customer){
            // 登陆成功
            // 把用户登陆信息保存至sessionStroage中
            setSessionStorage("customer", customer);
            // 返回上一页面
            router.go(-1)
        }
        else{
            // 登陆失败
            alert("用户名或密码错误！")
        }
    }).catch(function(err){
        console.log(err)
    })
}
</script>

<template>
<div class="container">

<header>
    <img src="../assets/logo.png">
    <p>让每个人都能享受科技的乐趣</p>
</header>

<section>
    <h1>小米账号登录</h1>
    <div class="box">
        <input type="text" v-model="telId" placeholder="请输入手机号">
    </div>
    <div class="box">
        <input type="password" v-model="password" placeholder="请输入密码">
    </div>
    <div class="btn" @click="login()">登 录</div>
    <div class="btn register" onclick="location.href='register.html'">注 册</div>

    <div style="width: 100%; height: 14.4vw;"></div>
</section>

<Footer></Footer>
</div>
</template>

<style scoped>
        /*********************** 总容器 *********************/
        .container {
            width: 100%;
            height: 100%;
        }

        /*********************** header 头部 *********************/
        header {
            width: 100%;
            height: 60vw;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        header img {
            width: 15vw;
            height: 15vw;
        }

        header p {
            font-size: 4vw;
            color: #999;
            margin-top: 4vw;
        }

        /*********************** 中间内容部分 *********************/
        section {
            width: 100%;
            box-sizing: border-box;
            padding: 0 10vw;
        }

        section h1 {
            font-size: 6vw;
            margin-bottom: 5vw;
        }

        section div {
            margin-bottom: 5vw;
        }

        section .box {
            width: 100%;
            height: 12vw;
            background-color: #F0F0F0;
            border-radius: 4vw;

            display: flex;
            align-items: center;
        }

        section .box input {
            width: 100%;
            box-sizing: border-box;
            padding-left: 4vw;

            border: none;
            outline: none;
            background-color: #F0F0F0;
        }

        section .btn {
            width: 100%;
            height: 12vw;
            border-radius: 6vw;
            background-color: #62BFFF;
            font-size: 4.6vw;
            color: #FFF;

            line-height: 12vw;
            text-align: center;
            user-select: none;
            cursor: pointer;
        }

        section .register {
            background-color: #89E45C;
        }
         
    </style>