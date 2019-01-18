<template>
	<div class="layout">
		<Layout>
			<Header>
				<Menu mode="horizontal" theme="dark" active-name="1">
					<div class="layout-logo">
						<span style="color: white;position: absolute;font-family: 华文琥珀;font-size: 21px;top:-17px">水果商城</span>
					</div>
					<div class="layout-nav">
						<MenuItem name="1">
						<!-- <Icon type="ios-keypad"></Icon> -->
						<span @mousemove="test">
							<Avatar src="https://i.loli.net/2017/08/21/599a521472424.jpg" />
							&ensp; {{adminUser}}
						</span>

						</MenuItem>
						<MenuItem name="2">
						<div @click="logout">
							<Icon type="md-exit" />
							注销
						</div>
						</MenuItem>
						<MenuItem name="3">
						<Icon type="ios-color-wand" />
						更换主题
						</MenuItem>
					</div>
				</Menu>
			</Header>
			<Layout>
				<!-- 左边功能区块 -->
				<Sider hide-trigger :style="{background: '#fff',overflow: 'auto'}">
					<Menu @on-open-change="parentTag" @on-select="childTag" active-name="1-1" :accordion="true" theme="light" width="auto"
					 :open-names="['1']">
						<Submenu name="商品管理">
							<template slot="title">
								<Icon type="ios-navigate"></Icon>
								商品管理
							</template>

							<router-link to="/commodityInfo">
								<MenuItem name="商品信息"> 商品信息</MenuItem>
							</router-link>
							<router-link to="/addCommodity">
								<MenuItem name="新增商品">新增商品</MenuItem>
							</router-link>
						</Submenu>
						<Submenu name="供应商管理">
							<template slot="title">
								<Icon type="ios-contacts" />
								供应商管理
							</template>

							<router-link to="/supplierInfo">
								<MenuItem name="供应商信息"> 供应商信息</MenuItem>
							</router-link>
							<router-link to="/addSupplier">
								<MenuItem name="新增供应商">新增供应商</MenuItem>
							</router-link>
						</Submenu>
						<Submenu name="用户管理">
							<template slot="title">
								<Icon type="ios-keypad"></Icon>
								用户管理
							</template>
							<router-link to="/customerInfo">
								<MenuItem name="用户信息">用户信息</MenuItem>
							</router-link>

						</Submenu>
						<Submenu name="进货管理">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								进货管理
							</template>
							<router-link to="/purchaseInfo">
								<MenuItem name="进货信息">进货信息</MenuItem>
							</router-link>
							<router-link to="/addPurchase">
								<MenuItem name="进货">进货</MenuItem>
							</router-link>
						</Submenu>
						<Submenu name="库存管理">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								库存管理
							</template>
							<router-link to="/stockInfo">
								<MenuItem name="库存信息">库存信息</MenuItem>
							</router-link>
						</Submenu>
						<Submenu name="快递管理">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								快递管理
							</template>
							<router-link to="/">
								<MenuItem name="快递信息">快递信息</MenuItem>
							</router-link>
						</Submenu>
						<Submenu name="基本功能">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								基本功能
							</template>
							<MenuItem name="商品数据导入">商品数据导入</MenuItem>
							<MenuItem name="商品数据导出">商品数据导出</MenuItem>
							<MenuItem name="订单数据导出">订单数据导出</MenuItem>
						</Submenu>
						<Submenu name="关于本系统">
							<template slot="title">
								<Icon type="ios-analytics"></Icon>
								关于本系统
							</template>
							<MenuItem name="系统说明">系统说明</MenuItem>
						</Submenu>
					</Menu>
				</Sider>
				<Layout :style="{padding: '0 24px 24px'}">
					<Breadcrumb :style="{margin: '24px 0'}">
						<BreadcrumbItem>{{Home}}</BreadcrumbItem>
						<BreadcrumbItem>{{Components}}</BreadcrumbItem>
						<BreadcrumbItem>{{Layout}}</BreadcrumbItem>
					</Breadcrumb>
					<!-- 内容区 -->
					<Content :style="{padding: '7px', minHeight: '455px', background: '#fff'}">
						<router-view></router-view>
					</Content>
				</Layout>
			</Layout>
		</Layout>
	</div>
</template>
<script>
	export default {
		data() {
			return {
				URL: '',
				adminUser: '',
				adminImgUrl: '',
				Home: "您的当前位置为",
				Components: "--",
				Layout: "--"
			};
		},
		methods: {
			parentTag(e) {
				this.Components = e[0];
				if (e[0] === undefined) {
					this.Components = "--"
					this.Layout = "--";
				}
			},
			test() {

			},
			childTag(e) {
				this.Layout = e;
			},
			logout() {
				axios.get("http://localhost:8888/admin/logout").then(resp => {
					this.$router.replace('/login');
				});
			}
		},
		created() {

			function sub(text) {
				return text.substring(0, 15) + "...";
			}

			//查询 session中的信息
			axios.get("http://localhost:8888/admin").then(resp => {
				if (resp.data.code == 202) {
					if (resp.data.data.adminUser.length > 16) {
						this.adminUser = sub(resp.data.data.adminUser);
					}
					this.adminUser = resp.data.data.adminUser;
					//this.adminImgUrl = resp.data.data.adminPhoto;
				} else {
					this.$router.replace('/login')
				}
			});
		}
	}
</script>

<style scoped>
	.layout {
		border: 1px solid #d7dde4;
		background: #f5f7f9;
		position: relative;
		border-radius: 4px;
		overflow: hidden;
	}

	.layout-logo {
		width: 100px;
		height: 30px;
		background: #5b6270;
		border-radius: 3px;
		float: left;
		position: relative;
		top: 15px;
		left: 20px;
	}

	.layout-nav {
		width: 420px;
		margin: 0 auto;
		margin-right: 20px;
	}
</style>
