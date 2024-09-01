<script setup>
import axios from "axios";
import { computed, ref } from "vue";
import { getSessionStorage } from "../common.js";
import { useRouter } from "vue-router";
const goodsList = ref([]);
const customer = getSessionStorage("customer");
const router = useRouter();
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
    totalPrice += goodsList.value[i].quantity * goodsList.value[i].state * goodsList.value[i].goods.goodsPrice;
  }
  return totalPrice;
});
function findGoodsInCart() {
  axios
    .post("findGoodsInCart?telId=" + customer.telId)
    .then(function (response) {
      goodsList.value = response.data;
    })
    .catch(function (err) {
      console.log(err);
    });
}
function init() {
  if (customer) {
    findGoodsInCart();
  } else {
    router.push("/login");
  }
}
init();
function changeQuantity(goodsId, num, quantity) {
  if (num == -1 && quantity <= 1) {
    alert("商品已不可被减少！");
    return 0;
  }
  axios
    .post("updateGoodsCount", {
      goodsId: goodsId,
      telId: customer.telId,
      quantity: num,
    })
    .then(function (response) {
      findGoodsInCart();
    })
    .catch(function (err) {
      console.log(err);
    });
}
function del(goodsId) {
  let flag = confirm("您确定删除此商品吗？");
  if (flag) {
    axios
      .post("deleteGoods", {
        goodsId: goodsId,
        telId: customer.telId,
      })
      .then(function (response) {
        findGoodsInCart();
      })
      .catch(function (err) {
        console.log(err);
      });
  }
}
function changeState(goodsId, state) {
  axios
    .post("updateState", {
      goodsId: goodsId,
      telId: customer.telId,
      state: state ? 0 : 1,
    })
    .then(function (response) {
      findGoodsInCart();
    })
    .catch(function (err) {
      console.log(err);
    });
}
function toIndex() {
  router.push("/");
}
function toSettlement() {
  router.push("/settlement");
}
</script>

<template>
  <div class="container">
    <header>
      <p>购物车</p>
    </header>

    <section>
      <div style="width: 100%; height: 13.9vw"></div>
      <img
        class="cart-null"
        src="../assets/cartnull.png"
        v-if="goodsList.length == 0"
      />
      <ul v-for="(cart, index) in goodsList" :key="index">
        <li>
          <div @click="changeState(cart.goods.goodsId, cart.state)">
            <i class="fa fa-check-circle" v-if="cart.state"></i>
            <i class="fa fa-circle-o" v-if="!cart.state"></i>
          </div>
          <div>
            <img :src="cart.goods.goodsImg" />
          </div>
          <div>
            <h3>{{ cart.goods.goodsName }}</h3>
            <p>售价：{{ cart.goods.goodsPrice }}元</p>
            <div class="quantity-box">
              <div class="quantity-box-left">
                <div
                  class="change"
                  @click="changeQuantity(cart.goods.goodsId, -1, cart.quantity)"
                >
                  -
                </div>
                <div class="quantity">{{ cart.quantity }}</div>
                <div
                  class="change"
                  @click="changeQuantity(cart.goods.goodsId, 1, cart.quantity)"
                >
                  +
                </div>
              </div>
              <div class="quantity-box-right" @click="del(cart.goods.goodsId)">
                <i class="fa fa-trash-o"></i>
              </div>
            </div>
          </div>
        </li>
      </ul>
      <div style="width: 100%; height: 14.4vw"></div>
    </section>

    <footer>
      <ul>
        <li>
          <p>共{{ cartTotalCount }}件</p>
          <p>金额：<span>{{ cartTotalPrice }}</span>元</p>
        </li>
        <li @click="toIndex()">继续购物</li>
        <li @click="toSettlement()" v-if="cartTotalCount">去结算</li>
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
  background-color: #eee;

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

section .cart-null {
  width: 100vw;
}

section ul {
  /*display:none;*/
  width: 100%;
}

section ul li {
  width: 100%;
  height: 28vw;
  box-sizing: border-box;
  padding: 3vw;
  background-color: #fff;
  margin-bottom: 3.6vw;

  display: flex;
}

section ul li > div:nth-child(1) {
  flex: 0 0 10vw;
  display: flex;
  justify-content: center;
  align-items: center;
}

section ul li > div:nth-child(2) {
  flex: 0 0 26vw;
}

section ul li > div:nth-child(3) {
  flex: 1;
  box-sizing: border-box;
  padding-left: 3vw;
}

section ul li > div:nth-child(1) i {
  font-size: 6vw;
  color: #ff6700;
}

section ul li > div:nth-child(2) img {
  width: 26vw;
  height: 22vw;
  box-sizing: border-box;
  border: solid 1px #ddd;
}

section ul li > div:nth-child(3) h3 {
  font-weight: 300;
  font-size: 4vw;
  color: #666;
  margin-bottom: 1vw;
}

section ul li > div:nth-child(3) p {
  font-size: 3.3vw;
  color: #999;
  margin-bottom: 1vw;
}

section ul li > div:nth-child(3) .quantity-box {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

section ul li > div:nth-child(3) .quantity-box .quantity-box-left {
  width: 24vw;
  height: 9vw;
  border: solid 1px #eee;
  display: flex;
}

section ul li > div:nth-child(3) .quantity-box .quantity-box-left .change {
  width: 9vw;
  height: 9vw;
  background-color: #f4f4f4;
  color: #666;
  font-size: 6vw;

  line-height: 9vw;
  text-align: center;
  user-select: none;
  cursor: pointer;
}

section ul li > div:nth-child(3) .quantity-box .quantity-box-left .quantity {
  width: 9vw;
  height: 9vw;
  color: #666;
  font-size: 5vw;

  line-height: 9vw;
  text-align: center;
  user-select: none;
  cursor: pointer;
}

section ul li > div:nth-child(3) .quantity-box .quantity-box-right {
  height: 9vw;
  line-height: 9vw;
}

section ul li > div:nth-child(3) .quantity-box .quantity-box-right i {
  color: #ccc;
  font-size: 6vw;
  margin-right: 2vw;

  user-select: none;
  cursor: pointer;
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
  font-size: 3.9vw;

  user-select: none;
  cursor: pointer;
}

footer ul li:nth-child(1) {
  flex-direction: column;
  font-size: 3.6vw;
  color: #999;
}

footer ul li:nth-child(1) p span {
  font-weight: 700;
  font-size: 5vw;
  color: #ff6700;
}

footer ul li:nth-child(2) {
  background-color: #f4f4f4;
  color: #333;
}

footer ul li:nth-child(3) {
  background-color: #ff6700;
  color: #fff;
}
</style>
