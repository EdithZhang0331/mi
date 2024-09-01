<script setup>
import axios from "axios";
import { useRouter } from "vue-router";
import { getSessionStorage } from "../common";
import { ref } from "vue";
const router = useRouter();
const customer = getSessionStorage("customer");
const goodsCountInCart = ref(0);
function init() {
  if (customer) {
    axios
      .get("findGoodsCountInCart?telId=" + customer.telId)
      .then(function (response) {
        goodsCountInCart.value = response.data.resData;
        // console.log(goodsCountInCart.value)
      })
      .catch(function (err) {
        console.log(err);
      });
  }
}
init();
// 跳转到首页
function toIndex() {
  router.push("/");
}
// 跳转到商品分类
function toGoodsType() {
  router.push("/goodsType");
}

function toCart() {
  if (customer) {
    router.push("/cart");
  } else {
    router.push("/login");
  }
}
function toMine() {
  if (customer) {
  } else {
    router.push("/login");
  }
}
</script>

<template>
  <footer>
    <ul>
      <li @click="toIndex">
        <i class="fa fa-home"></i>
        <p>首页</p>
      </li>
      <li @click="toGoodsType">
        <i class="fa fa-bars"></i>
        <p>分类</p>
      </li>
      <li>
        <i class="fa fa-life-bouy"></i>
        <p>米圈</p>
      </li>
      <li @click="toCart()" class="cart">
        <i class="fa fa-shopping-cart"></i>
        <p>购物车</p>
        <div class="quantity" v-if="goodsCountInCart">
          {{ goodsCountInCart }}
        </div>
      </li>
      <li @click="toMine()">
        <i class="fa fa-user-o"></i>
        <p>我的</p>
      </li>
    </ul>
  </footer>
</template>

<style scoped>
/************************* 底部 **************************/
footer {
  width: 100%;
  height: 14.4vw;
  background-color: #fff;
  box-shadow: 0 3px 14px 2px #ccc;

  position: fixed;
  left: 0;
  bottom: 0;
}

footer ul {
  width: 100%;
  height: 14.4vw;
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
}

footer ul li .fa {
  font-size: 5vw;
  color: #666;
  margin-bottom: 1.2vw;
}

footer ul .cart {
  position: relative;
}

footer ul .cart .quantity {
  width: 4.4vw;
  height: 4.4vw;
  border-radius: 2.2vw;
  background-color: #ff6700;
  font-size: 3.3vw;
  color: #fff;
  text-align: center;
  line-height: 4.4vw;

  position: absolute;
  left: 7vw;
  top: -1.2vw;
}
</style>
