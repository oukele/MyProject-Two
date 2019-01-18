<template>
	<div>
		<Row>
			<Col span="2">&nbsp;</Col>
			<Col span="8">
			<Input search enter-button @on-search='searchSupplierAddress' placeholder="请输入要查询的供应商地址..." />
			</Col>
			<Col span="2">&nbsp;</Col>
			<Col span="4">
			<RadioGroup v-model="btnSupplierStatus" @on-change='searchSupplierStatus' type="button">
				<Radio label="全部"></Radio>
				<Radio label="正常"></Radio>
				<Radio label="异常"></Radio>
			</RadioGroup>
			</Col>
		</Row>
		<Row>
			<Col span="24">
			<template>
				<Table :loading="loading" :columns="columns10" height="388" :data="supplierInfo"></Table>
			</template>
			<div style="float: right;" :hidden=page_hidden>
				<Page :total="page_total" :current="1" @on-change="changePage($event)"></Page>
			</div>

			</Col>
		</Row>

		<!-- 修改弹出层 -->
		<Modal v-model="show_edit" @on-ok="btn_update" title="编辑供应商信息" width="600">
			<Form :model="supplierUpdateForm" label-position="left" :label-width="100">
				<FormItem label="供应商名称">
					<Input v-model="supplierUpdateForm.supplierName" placeholder="请填写供应商名称...."></Input>
				</FormItem>
				<FormItem label="供应商地址">
					<Input v-model="supplierUpdateForm.supplierAddress" placeholder="请填写供应商地址...."></Input>
				</FormItem>
				<FormItem label="供应商电话">
					<Input v-model="supplierUpdateForm.supplierTel" placeholder="请填写供应商电话...."></Input>
				</FormItem>
				<FormItem label="供应商备注">
					<Input v-model="supplierUpdateForm.supplierRemarks" type="textarea" :autosize="{minRows: 4,maxRows: 6}"
					 placeholder="请输入供应商备注...."></Input>
				</FormItem>
			</Form>
		</Modal>

		<!-- 删除弹出层 -->
		<Modal v-model="del_show" width="360">
			<p slot="header" style="color:#f60;text-align:center">
				<Icon type="ios-information-circle"></Icon>
				<span>删除提醒？</span>
			</p>
			<div style="text-align:center">
				<p>您清楚,您现在所做的操作吗？<p style="color:red">本系统没有数据恢复功能哦</p>
				</p>
				<p>您确定要删除吗？</p>
			</div>
			<div slot="footer">
				<Button type="error" size="large" long :loading="modal_loading" @click="del">确定删除</Button>
			</div>
		</Modal>

	</div>
</template>

<script>
	import expandRow from './tableRowExpand.vue'
	export default {
		components: {
			expandRow
		},
		methods: {
			changePage(page) {
				this.btnSupplierStatus ="全部"; 
				axios.get(this.URL + "/supplier", {
					params: {
						page: page
					}
				}).then(resp => {
					this.supplierInfo = resp.data.data;
					this.loading = false;
				});
				axios.get(this.URL + "/supplier/getTotal").then(resp => {
					this.page_total = resp.data.data;
				});
				this.page_hidden = false;

			},
			searchSupplierAddress(e) { //按照地址进行 搜索
				if( e.trim().length == 0  ){
					this.changePage();
					return false;
				}
				axios.get(this.URL + "supplier/supplierAddress", {
					params: {
						supplierAddress: e
					}
				}).then(resp => {
					if( resp.data.code == 202){
						this.supplierInfo = resp.data.data;
						this.page_hidden = true;
					}else{
						this.$Message.success(resp.data.data);
						this.supplierInfo = [];
						this.page_hidden = true;
					}
				});
			},
			searchSupplierStatus(e) { //按照状态进行模糊查询
				let condition = -1;
				if (e == "正常") {
					condition = 1;
				} else if (e == "异常") {
					condition = 0;
				} else {
					condition = 2;
				}

				if (condition == 2) {
					this.changePage();
					return false;
				}
				axios.get(this.URL + "supplier/supplierStatus", {
					params: {
						supplierStatus: condition
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.supplierInfo = resp.data.data;
						this.page_hidden = true;
					} else {
						this.$Message.success(resp.data.data);
						this.supplierInfo = [];
						this.page_hidden = true;
					}
				});
			},
			supplierEdit(row) { //编辑供应商信息
				this.show_edit = true;
				this.supplierUpdateForm.supplierId = row.supplierId;
				this.supplierUpdateForm.supplierName = row.supplierName;
				this.supplierUpdateForm.supplierAddress = row.supplierAddress;
				this.supplierUpdateForm.supplierTel = row.supplierTel;
				this.supplierUpdateForm.supplierRemarks = row.supplierRemarks;
			},
			changeStatus(id, status) { //修改供应商状态
				this.loading = true; //显示加载中....
				axios.put(this.URL + "supplier/" + id + "/" + status + "").then(resp => {
					if (resp.data.code == "202") {
						this.$Message.success(resp.data.data);
					} else {
						alert("出现异常,请联系管理员。");
					}
					this.loading = false; //关闭加载中...
				});
			},
			remove(id) { //删除供应商
				this.supplierId = id;
				this.del_show = true;
			},
			del() { //删除供应商
				let that = this;
				if (this.supplierId > 0) {
					this.modal_loading = true; //加载圈

					axios.delete(this.URL + "supplier/" + this.supplierId).then(resp => {

						if (resp.data.code == 202) {
							this.supplierInfo = this.supplierInfo.filter((e) => {
								return e.supplierId != that.supplierId
							}); //移除商品
							this.del_show = false; //弹出窗关闭
							this.$Message.success(resp.data.data);
						} else {
							this.del_show = false;
							this.$Message.success(resp.data.data);
						}
					});
				}
				this.modal_loading = false;
			},
			btn_update() { //修改供应商信息
				axios.put(this.URL + "supplier", this.supplierUpdateForm, {
					"Content-Type": "application/json"
				}).then(resp => {
					if (resp.data.code == 202) {
						this.$Message.success(resp.data.data);
					} else {
						this.$Message.success(resp.data.data);
					}
				});
				//刷新页面数据
				this.changePage();
			}
		},
		created() {
			this.changePage();
		},
		data() {
			return {
				URL: 'http://localhost:8888/',
				btnSupplierStatus: "全部",
				page_hidden: false,
				del_show: false,
				show_edit: false,
				modal_loading: false,
				loading: true,
				supplierId: 0,
				page_total: 0,
				columns10: [{
						type: 'expand',
						width: 50,
						render: (h, params) => {
							return h(expandRow, {
								props: {
									row: params.row
								}
							})
						}
					},
					{
						title: '供应商名称',
						key: 'supplierName'
					},
					{
						title: '供应商地址',
						key: 'supplierAddress'
					},
					{
						title: '联系电话',
						key: 'supplierTel'
					},
					{
						title: '状态',
						key: 'supplierStatus',
						render: (h, params) => {
							//return h('元素',{元素的性质},'内容')
							return h('i-switch', {
								props: { //设置属性
									size: 'large',
									value: params.row.supplierStatus //通过value控制switch开关 （true,false）
								},
								scopedSlots: {
									open: () => h('span', '正常'),
									close: () => h('span', '异常')
								},
								on: {
									'on-change': (value) => {
										//修改状态,参数为 供应商id,状态值
										this.changeStatus(params.row.supplierId, value);
									}
								}
							});
						}
					},
					{
						title: '操作',
						// key: 'action',
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
											this.supplierEdit(params.row);
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
											this.remove(params.row.supplierId)
										}
									}
								}, '删除')
							]);
						}
					}
				],
				supplierInfo: [],
				supplierUpdateForm: {
					supplierId: 0,
					supplierName: '',
					supplierAddress: '',
					supplierTel: '',
					supplierRemarks: ''
				}
			}
		}
	}
</script>

<style>
	.expand-row {
		margin-bottom: 16px;
	}
</style>
