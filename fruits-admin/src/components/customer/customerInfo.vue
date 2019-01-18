<template>
	<div>
		<Row>
			<Col span="2">&nbsp;</Col>
			<Col span="8">
			<Input search enter-button @on-search='searchCustomer' placeholder="请输入关键字..." />
			</Col>
			<Col span="2">&nbsp;</Col>
			<Col span="4">

			</Col>
			<Col span="4">&nbsp;</Col>
			<Col span="4">
			<!-- <Button type="primary" @click='addStock'>入库</Button> -->
			</Col>
		</Row>
		<Row>
			<Col span="24">
			<template>
				<Table :loading="loading" :columns="columns1" height="388" :data="customerInfo"></Table>
			</template>
			<div style="float: right;" :hidden=page_hidden>
				<Page :total="page_total" :current="1" @on-change="changePage($event)"></Page>
			</div>

			</Col>
		</Row>

	<!-- 修改弹出层 -->
		<Modal v-model="show_edit" @on-ok="btn_update" title="编辑用户信息" width="600">
			<span style="color:red">用户信息，不可乱更改.........</span>
			<Form :model="customerUpdateForm" label-position="left" :label-width="100">
				<p>用户ID:{{customerUpdateForm.customerId}}</p>
				<p>账号：{{customerUpdateForm.customerUser}}</p>
				<p>姓名：{{customerUpdateForm.customerName}}</p>
				<p>电话号码：{{customerUpdateForm.customerTel}}</p>
				<p>身份证：{{customerUpdateForm.customerCard}}</p>
			</Form>
		</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				URL: "http://localhost:8888/",
				page_total: 0,
				modal_loading: false,
				page_hidden: true,
				loading: false,
				searchText: '',
				show_edit:false,
				columns1: [{
						title: '用户编号',
						key: 'customerId'
					},
					{
						title: "用户照片",
						key: "customerPhoto",
						align: 'center',
						render: (h, params) => {
							return h('img', {
								style: {
									width: '100px',
									'height': '80px',
									'border-radius': '5%'
								},
								attrs: {
									src: 'http://localhost:8888/' + params.row.commodityPhoto
								}
							});
						}
					},
					{
						title: "用户账号",
						key: "customerUser",
						align: 'center'
					},
					{
						title: '用户姓名',
						key: 'customerName',
						align: 'center'
					},
					{
						title: '用户电话',
						key: 'customerTel',
						align: 'center'
					},
					{
						title: "用户身份证",
						key: "customerCard",
						align: 'center',
						tooltip: true
					},
					{
						title: '用户状态',
						key: 'customerState',
						align: 'center',
						render: (h, params) => {
							//return h('元素',{元素的性质},'内容')
							return h('i-switch', {
								props: { //设置属性
									size: 'large',
									value: params.row.customerState //通过value控制switch开关 （true,false）
								},
								scopedSlots: {
									open: () => h('span', '启用'),
									close: () => h('span', '禁用')
								},
								on: {
									'on-change': (value) => {
										//修改状态,参数为 商品id,状态值
										this.changeStatus(params.row.customerId, value);
									}
								}
							});
						}
					},
					{
						title: "操作",
						align: "center",
						render: (h, params) => {
							return h('div', [
								h('Button', {
									props: {
										type: 'primary',
										size: 'small'
									},
									style: {
										marginRight: '5px'
									},
									on: {
										click: () => {
											this.customerEdit(params.row);
										}
									}
								}, '编辑'),
								h('Button', {
									props: {
										type: 'error',
										size: 'small'
									},
									on: {
										click: () => {
											this.remove(params.row.customerId)
										}
									}
								}, '删除')
							]);
						}
					}
				],
				customerInfo: [],
				customerUpdateForm:{}
			}
		},
		methods: {
			customerEdit(ev){//编辑
				this.show_edit = true;
				this.customerUpdateForm = ev;
			},
			btn_update(){
				this.$Message.success("您点击了确定");
			},
			remove(e){
				this.$Message.error("这是一位重要的客户,不能删除。 Id为："+e);
			},
			searchCustomer(e) { //模糊搜索
				this.searchText = e;
				axios.get(this.URL + "customer/search", {
					params: {
						searchName: e
					}
				}).then(resp => {
					this.customerInfo = resp.data.data;
					this.page_total = resp.data.total;
				});
			},
			changeStatus(ev, value) { //修改状态
				axios.get(this.URL + "customer/updateState/" + ev + "/" + value).then(resp => {
					if( resp.data.code == 202 ){
						this.$Message.success(resp.data.data);
					}else{
						this.$Message.error(resp.data.data);
					}
				});
			},
			changePage(ev) { //分页
				this.loading = true;

				if (this.searchText.length > 0) {
					axios.get(this.URL + "customer/search", {
						params: {
							searchName: this.searchText,
							page: ev
						}
					}).then(resp => {
						this.customerInfo = resp.data.data;
						this.page_total = resp.data.total;
					});
				}

				this.dataTemple(ev);
				this.loading = false;
				this.page_hidden = false;
			},
			//商品数据模板
			dataTemple(page) {
				//时间转换
				function takeTime(date) {
					let time = new Date(date);
					return time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + " " +
						time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
				};
				this.stockInfo = []; //数组清空
				axios.get(this.URL + "customer", {
					params: {
						page: page
					}
				}).then(resp => {
					this.customerInfo = resp.data.data;
					this.page_total = resp.data.total;
				});
			}
		},
		created() {
			this.changePage();
		}
	}
</script>
