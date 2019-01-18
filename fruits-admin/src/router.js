import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
	routes: [
		{
			path: "/",
			redirect: "/login"
		},
		{
			path:'/login',
			name:'login',
			component: () => import('./views/login.vue')
		},
		{
			path:'/index',
			name:'index',
			component: () => import('./views/index.vue'),
			children:[
				{
					path: '/commodityInfo',
					name: 'commodityInfo',
					component: () => import('./components/commodity/commodityInfo.vue')
				},
				{
					path: '/addCommodity',
					name: 'addCommodity',
					component: () => import('./components/commodity/addCommodity')
				},
				{
					path: '/supplierInfo',
					name: 'supplierInfo',
					component: () => import('./components/supplier/supplierInfo')
				},
				{
					path: '/addSupplier',
					name: 'addSupplier',
					component: () => import('./components/supplier/addSupplier')
				},
				{
					path: '/purchaseInfo',
					name: 'purchaseInfo',
					component: () => import('./components/purchase/purchaseInfo')
				},
				{
					path: '/addPurchase',
					name: 'addPurchase',
					component: () => import('./components/purchase/addPurchase')
				},
				{
					path: '/stockInfo',
					name: 'stockInfo',
					component: () => import('./components/stock/stockInfo')
				},
				{
					path:'/customerInfo',
					name:'customerInfo',
					component:()=>import('./components/customer/customerInfo')
				}
			]
		}
		//     {
		//       path: '/about',
		//       name: 'about',
		//       // route level code-splitting
		//       // this generates a separate chunk (about.[hash].js) for this route
		//       // which is lazy-loaded when the route is visited.
		//       component: () => import(/* webpackChunkName: "about" */ './views/About.vue')
		//     }
	],
	mode: 'hash'
})
