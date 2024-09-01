<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import {getSessionStorage} from "../common.js";
const goods = ref({})
const route = useRoute()
const router = useRouter()
// 获取当前登陆用户的信息
const customer = getSessionStorage("customer")
let goodsId = 0;
let findGoodsCountRes = ref(0);
let goodsCountInCart = ref(0);
// 根据用户ID查询购物车中商品的数量
function findGoodsCountInCart(telId){
    axios.get("findGoodsCountInCart?telId=" + telId).then(function(response){
        goodsCountInCart.value = response.data.resData
        // console.log(goodsCountInCart.value)
    }).catch(function(err){
        console.log(err)
    })
}
function init(){
    // 根据商品Id查询商品详细信息
    goodsId = route.query.goodsId;
    axios.get("findGoodsInformation?goodsId=" + goodsId).then(function(response){
        goods.value = response.data
    }).catch(function(err){
        console.log(err)
    })
    if(customer){
        findGoodsCountInCart(customer.telId)
    }
    
}
init()
function toIndex(){
    router.push("/")
}
function toCart(){
    router.push("/cart")
}
function toLogin(){
    router.push("/login")
}
function addToCart(){
    if(customer){
        // 根据telId和goodsId查询购物车中是否包含当前商品
        // json数据仅可以使用post方法传输
        axios.post("findGoodsCount",{
            telId:customer.telId, 
            goodsId:goodsId
        }).then(function(response){
            findGoodsCountRes = response.data.resData
            // console.log(findGoodsCountRes)
            // 包含，更新数量
            if(findGoodsCountRes >= 1){
                // console.log(findGoodsCountRes)
                axios.post("updateGoodsCount",{
                    goodsId:goodsId, 
                    telId:customer.telId, 
                    quantity:1
                }).then(function(response){
                    alert("添加数量成功！")
                }).catch(function(err){
                    console.log(err)
                })
            }
            //不包含，插入数据
            else{
                // console.log(findGoodsCountRes)
                axios.post("insertGoodsToCart",{
                    goodsId:goodsId, 
                    telId:customer.telId, 
                    quantity:1, 
                    state:0
                }).then(function(response){
                    alert("添加商品成功！")
                    findGoodsCountInCart(customer.telId)
                }).catch(function(err){
                    console.log(err)
                })
                
            }
            }).catch(function(err){
                console.log(err)
        })

    }
    else{
        router.push("/login");
    }
    
}
</script>
<template>
    <div class="container">
        <section>
            <div class="login" @click="toLogin()">
                <p>登陆后享受更多优惠</p>
                <p>去登陆 <i class="fa fa-angle-right"></i></p>
            </div>
            <img :src=goods.goodsImg />
            <div class="price">
                <p>{{ goods.goodsPrice }}</p>
            </div>
            <div class="info">
                <h3>{{ goods.goodsName }}</h3>
                <p v-html="goods.goodsExplain"></p>
            </div>
            <div style="width: 100%; height: 14.4vw"></div>
        </section>

        <footer>
            <ul>
                <li @click="toIndex()">
                    <i class="fa fa-home"></i>
                    <p>首页</p>
                </li>
                <li>
                    <i class="fa fa-volume-control-phone"></i>
                    <p>客服</p>
                </li>
                <li @click="toCart()">
                    <i class="fa fa-shopping-cart"></i>
                    <p>购物车</p>
                    <div class="quantity" v-if="goodsCountInCart>0">{{ goodsCountInCart }}</div>
                </li>
            </ul>
            <div class="cart" @click="addToCart()">
                <div class="cart-box">加入购物车</div>
            </div>
        </footer>
    </div>
</template>
<style scoped>
        /*********************** 总容器 *********************/
        .container {
            width: 100%;
            height: 100%;
        }

        /*********************** section 部分 *********************/

        section {
            width: 100%;
        }

        section .login {
            width: 100%;
            height: 10vw;
            box-sizing: border-box;
            padding: 0 4.4vw;
            font-size: 4.2vw;

            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        section .login p:last-child {
            font-size: 3.6vw;
            color: #666;
            user-select: none;
            cursor: pointer;
        }

        section .login p:last-child i {
            font-size: 4.6vw;
            margin-left: 2vw;
        }

        section img {
            width: 100%;
            height: 100vw;
            box-shadow: 0 2px 4px -1px #e5e5e5;
        }

        section .price {
            width: 100%;
            height: 14vw;
            box-sizing: border-box;
            padding-left: 4.4vw;
            display: flex;
            align-items: center;

            font-size: 6.4vw;
            font-weight: 700;
            color: #ff6700;
        }

        section .price p {
            font-size: 6.4vw;
            font-weight: 700;
            color: #ff6700;
        }

        section .price p span {
            font-size: 3.2vw;
            vertical-align: text-top;
        }

        section .info {
            width: 100%;
            box-sizing: border-box;
            padding-left: 4.4vw;
        }

        section .info h3 {
            font-size: 4.4vw;
            margin-bottom: 3vw;
        }

        section .info p {
            font-size: 3.3vw;
        }

        /*********************** footer 部分 *********************/
        footer {
            width: 100%;
            height: 14.4vw;
            background-color: #fff;
            box-shadow: 0 3px 14px 2px #ccc;

            display: flex;
            align-items: center;

            position: fixed;
            left: 0;
            bottom: 0;
        }

        footer ul {
            flex: 2;

            display: flex;
            justify-content: space-around;
            align-items: center;

            font-size: 3.3vw;
            color: #999;
        }

        footer ul li {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

            user-select: none;
            cursor: pointer;

            position: relative;
        }

        footer ul li .fa {
            font-size: 5vw;
            color: #666;
            margin-bottom: 1.2vw;
        }

        footer ul li:last-child .quantity {
            width: 4.4vw;
            height: 4.4vw;
            background-color: #ff6700;
            text-align: center;
            line-height: 4.4vw;
            color: #fff;
            font-size: 3.3vw;
            border-radius: 2.2vw;

            position: absolute;
            left: 7vw;
            top: -1.2vw;
        }

        footer .cart {
            flex: 3;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        footer .cart .cart-box {
            width: 80%;
            height: 10vw;
            line-height: 10vw;
            text-align: center;
            background-image: linear-gradient(to right, #ff9e22, #ff5934);
            border-radius: 5vw;

            font-size: 4vw;
            color: #fff;

            user-select: none;
            cursor: pointer;
        }
</style>
