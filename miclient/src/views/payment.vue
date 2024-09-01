<script setup>
import Footer from '../components/footer.vue';
import { getSessionStorage } from '../common';
import { useRoute } from 'vue-router';
import { ref } from 'vue';
import axios from 'axios';
const route = useRoute()
const customer = getSessionStorage("customer")
let orderId = ref(0)
const payment = ref([])
let firstPayment = ref({})
function init(){
    orderId = route.query.orderId;
    axios
    .post("findPayment?orderId=" + orderId)
    .then(function (response) {
        payment.value = response.data
        firstPayment = payment.value[0]
    })
    .catch(function (err) {
      console.log(err);
    });
}
init()
</script>

<template>
  <div class="container">
    <header>
      <p>支付平台页面</p>
    </header>

    <section>
      <div style="width: 100%; height: 13.9vw"></div>
      <div class="payment-info">第三方支付平台页面</div>
      <div class="order-info">
        <p>订单编号：{{ orderId }}</p>
        <p>订单金额：￥{{ firstPayment.orders && firstPayment.orders.orderTotal }}</p>
        <p>客户编号：{{ customer.telId }}</p>
        <p>客户名称：{{ customer.customerName }}</p>
        <p>订单状态：等待付款</p>
        <p v-if="firstPayment.orders && firstPayment.orders.address">收货地址：{{ firstPayment.orders.address.address }}</p>
        <p v-if="firstPayment.orders && firstPayment.orders.address">联 系 人：{{ firstPayment.orders.address.contactName }}</p>
        <p v-if="firstPayment.orders && firstPayment.orders.address">联系电话：{{ firstPayment.orders.address.contactTel }}</p>
        <p>订购商品：</p>
        <p v-for="(pm, index) in payment" :key="index">{{ pm.goods.goodsName }} x {{ pm.quantity }} <br /></p>
      </div>
      <div style="width: 100%; height: 14.4vw"></div>
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
  height: 13.9vw;
  background-color: #eee;

  position: fixed;
  left: 0;
  top: 0;
  z-index: 100;
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

section .payment-info {
  width: 80vw;
  margin: 0 auto;
  font-size: 5vw;
  line-height: 30vw;
  text-align: center;
}

section .order-info {
  width: 80vw;
  margin: 0 auto;
  font-size: 3.6vw;
  color: #333;
}

section .order-info p {
  margin-bottom: 1vw;
}
</style>
