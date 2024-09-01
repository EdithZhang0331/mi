import { createWebHashHistory, createRouter } from 'vue-router'

import Index from '../views/index.vue'
import Goods from '../views/goods.vue'
import GoodsType from '../views/goodsType.vue'
import Cart from '../views/cart.vue'
import Login from '../views/login.vue'
import Settlement from '../views/settlement.vue'
import Payment from '../views/payment.vue'

const router = createRouter({
    history: createWebHashHistory(),
    routes:[
        {
            path:"/",
            component: Index
        },
        {
            path:'/goods',
            component: Goods
        },
        {
            path:'/goodsType',
            component: GoodsType
        },
        {
            path:'/cart',
            component: Cart
        },
        {
            path:'/login',
            component: Login
        },
        {
            path:'/settlement',
            component: Settlement
        },
        {
            path:'/payment',
            component: Payment
        }
    ],
  })

export default router