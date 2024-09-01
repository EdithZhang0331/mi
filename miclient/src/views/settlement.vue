<script setup>
import { getCurDate, getCurTime, getSessionStorage } from "../common";
import axios from "axios";
import { computed, ref } from "vue";
import router from "../router";
const customer = getSessionStorage("customer");
const goodsList = ref([]);
const defaultAdd = ref("");
const orderId = ref();
function init() {
  axios
    .post("findDefaultAddress?telId=" + customer.telId)
    .then(function (response) {
      defaultAdd.value = response.data;
    })
    .catch(function (err) {
      console.log(err);
    });
  axios
    .post("findGoodsInCart?telId=" + customer.telId)
    .then(function (response) {
      let arr = response.data;
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].state == 1) {
          goodsList.value.push(arr[i]);
        }
      }
    })
    .catch(function (err) {
      console.log(err);
    });
}
init();
// 使用计算属性计算总数量及总金额
let cartTotalCount = computed(function () {
  let totalCount = 0;
  for (let i = 0; i < goodsList.value.length; i++) {
    totalCount += goodsList.value[i].quantity * goodsList.value[i].state;
  }
  return totalCount;
});
let cartTotalPrice = computed(function () {
  let totalPrice = 0;
  for (let i = 0; i < goodsList.value.length; i++) {
    totalPrice +=
      goodsList.value[i].quantity *
      goodsList.value[i].state *
      goodsList.value[i].goods.goodsPrice;
  }
  return totalPrice;
});
function toPayment() {
  // 创建订单
  axios
    .post("insertOrder", {
      telId: customer.telId,
      orderDate: new Date(),
      orderTotal: cartTotalPrice.value,
      addressId: defaultAdd.value.addressId,
      orderState: 0,
    })
    .then(function (response) {
      orderId.value = response.data.resData;
      router.push({
        path: '/payment', 
        query: { 
            orderId : orderId.value     //同名冒号后可省略
        }
    }) 
    })
    .catch(function (err) {
      console.log(err);
    });
}
</script>

<template>
  <div class="container">
    <header>
      <p>用户结算</p>
    </header>

    <section>
      <div style="width: 100%; height: 13.9vw"></div>
      <div class="address">
        <ul>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
          <li></li>
        </ul>
        <div class="content">
          <div class="content-left">
            <h3>{{ customer.customerName }} {{ customer.telId }}</h3>
            <p>{{ defaultAdd.address }}</p>
          </div>
          <div class="content-right" onclick="location.href='addressList.html'">
            <i class="fa fa-angle-right"></i>
          </div>
        </div>
      </div>
      <div class="detailed">
        <ul>
          <li v-for="(cart, index) in goodsList" :key="index">
            <div class="detailed-left">
              <img :src="cart.goods.goodsImg" />
              <p>{{ cart.goods.goodsName }}</p>
            </div>
            <div class="detailed-right">
              <p>￥{{ cart.goods.goodsPrice }}</p>
              <p>x {{ cart.quantity }}</p>
            </div>
          </li>
        </ul>
      </div>
      <div class="payment">
        <ul>
          <li>
            <div class="payment-left">
              <img src="../assets/payment01.png" />
              <p>支付宝</p>
            </div>
            <input type="radio" checked />
          </li>
          <li>
            <div class="payment-left">
              <img src="../assets/payment02.png" />
              <p>小米钱包</p>
            </div>
            <input type="radio" />
          </li>
          <li>
            <div class="payment-left">
              <img src="../assets/payment03.png" />
              <p>微信支付</p>
            </div>
            <input type="radio" />
          </li>
        </ul>
      </div>
      <div style="width: 100%; height: 14.4vw"></div>
    </section>

    <footer>
      <ul>
        <li>共{{ cartTotalCount }}件 合计：{{ cartTotalPrice }}</li>
        <li @click="toPayment()">去付款</li>
      </ul>
    </footer>
  </div>
</template>

<style scoped>
/*********************** 总容器 *********************/
.container {
  width: 100%;
  height: 100%;
  background-color: #eee;
}

/*********************** header 头部 *********************/
header {
  width: 100%;
  height: 13.9vw;

  position: fixed;
  left: 0;
  top: 0;
  z-index: 100;

  /*box-shadow: 0 2px 4px -1px #CCC;*/
}

header p {
  line-height: 13.9vw;
  text-align: center;
  font-size: 4.2vw;
  color: #666;
}

/*********************** section 部分 *********************/
section {
  width: 100%;
}

section .address {
  width: 100%;
  background-color: #fff;
  border-top: solid 1px #ddd;
  border-bottom: solid 1px #ddd;
  margin-bottom: 1.8vw;
}

section .address ul {
  width: 100%;
  height: 1vw;
  display: flex;
  justify-content: space-between;
}

section .address ul li {
  flex: 1;
  height: 1vw;
  margin: 0 0.6vw;
  transform: skew(-30deg);
}

section .address ul li:nth-child(odd) {
  background-color: #ff8a81;
}

section .address ul li:nth-child(even) {
  background-color: #8fcaf9;
}

section .address .content {
  width: 100%;
  box-sizing: border-box;
  padding: 5.6vw;

  display: flex;
  justify-content: space-between;
}

section .address .content .content-left {
  flex: 0 0 80vw;
}

section .address .content .content-left h3 {
  font-size: 4.2vw;
  color: #3c3c3c;
  margin-bottom: 2vw;
}

section .address .content .content-left p {
  font-size: 3.3vw;
  color: #757575;
}

section .address .content .content-right {
  flex: 1;
  display: flex;
  justify-content: flex-end;
  align-items: center;

  user-select: none;
  cursor: pointer;
}

section .address .content .content-right i {
  font-size: 6vw;
  color: #3c3c3c;
}

section .detailed {
  width: 100%;
  margin-bottom: 1.8vw;
  background-color: #fff;
  border-top: solid 1px #ddd;
  border-bottom: solid 1px #ddd;
}

section .detailed .info {
  width: 100%;
  box-sizing: border-box;
  padding: 3.6vw 5vw;
  font-size: 3.3vw;
  color: #3c3c3c;
}

section .detailed ul {
  width: 100%;
}

section .detailed ul li {
  width: 100%;
  height: 18vw;
  box-sizing: border-box;
  padding: 2vw 5vw;
  border-top: solid 1px #eee;

  display: flex;
  align-items: center;
  justify-content: space-between;

  font-size: 3.3vw;
  color: #3c3c3c;
}

section .detailed ul li .detailed-left {
  display: flex;
  align-items: center;
}

section .detailed ul li .detailed-left img {
  width: 16vw;
  height: 14vw;
  margin-right: 2vw;
}

section .detailed ul li .detailed-right {
  text-align: right;
}

section .detailed ul li .detailed-right p:last-child {
  margin-top: 2vw;
  color: #bbb;
}

section .payment {
  width: 100%;
  border-top: solid 1px #ddd;
  border-bottom: solid 1px #ddd;
  background-color: #fff;
}

section .payment ul {
  width: 100%;
}

section .payment ul li {
  width: 89vw;
  height: 12.5vw;
  margin: 0 auto;
  border-bottom: solid 1px #eee;

  display: flex;
  justify-content: space-between;
  align-items: center;
}

section .payment ul li .payment-left {
  display: flex;
  align-items: center;
}

section .payment ul li .payment-left img {
  width: 8.9vw;
  height: 8.9vw;
  margin-right: 3vw;
}

section .payment ul li .payment-left p {
  font-size: 3.6vw;
  color: #333;
}

section .payment ul li input {
  width: 4.5vw;
  height: 4.5vw;
}

/*********************** footer 部分 *********************/
footer {
  width: 100%;
  height: 14.4vw;
  background-color: #fff;
  box-shadow: 0 3px 14px 2px #ccc;

  position: fixed;
  left: 0;
  bottom: 0;

  display: flex;
}

footer ul {
  width: 100%;
  display: flex;
}

footer ul li {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 4.2vw;
  color: #ff6700;

  user-select: none;
  cursor: pointer;
}

footer ul li:last-child {
  background-color: #ff6700;
  color: #fff;
}
</style>
