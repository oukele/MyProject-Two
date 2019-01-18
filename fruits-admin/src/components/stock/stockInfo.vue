<template>
	<div>
		<Row>
			<Col span="2">&nbsp;</Col>
			<Col span="8">
			<Input search enter-button @on-search='searchStock' placeholder="请输入关键字..." />
			</Col>
			<Col span="2">&nbsp;</Col>
			<Col span="4">

			</Col>
			<Col span="4">&nbsp;</Col>
			<Col span="4">
			<Button type="primary" @click='addStock'>入库</Button>
			</Col>
		</Row>
		<Row>
			<Col span="24">
			<template>
				<Table :loading="loading" :columns="columns1" height="388" :data="stockInfo"></Table>
			</template>
			<div style="float: right;" :hidden=page_hidden>
				<Page :total="page_total" :current="1" @on-change="changePage($event)"></Page>
			</div>

			</Col>
		</Row>

		<!-- 修改弹出层 -->
		<Modal v-model="show_edit" @on-ok="btn_update" title="编辑商品信息" width="600">
			<Form :model="stockUpdateForm" label-position="left" :label-width="100">
				<FormItem label="进货表编号">
					<Input v-model="stockUpdateForm.purchaseId" disabled></Input>
				</FormItem>
				<FormItem label="商品标检">
					<Input v-model="stockUpdateForm.commodityTagName" disabled></Input>
				</FormItem>
				<FormItem label="库存数量">
					<InputNumber disabled v-model="stockUpdateForm.stockCount" style="width: 100%;" :min="1"></InputNumber>
				</FormItem>
				<FormItem label="商品单位">
					<Input v-model="stockUpdateForm.commodityCompanyName" disabled></Input>
				</FormItem>
				<FormItem label="入库时间">
					<Input disabled v-model="stockUpdateForm.stockDatetime"></Input>
				</FormItem>
				<FormItem label="库存备注">
					<Input v-model="stockUpdateForm.stockRemarks"></Input>
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

		<!-- 入库弹出层 -->
		<Modal v-model="addStockModal" @on-ok='subAddStock' title="入库" width="500">
			<Form :model="stockAddForm" label-position="left" :label-width="100">
				<FormItem label="进货编号">
					<Cascader v-model="stockAddInfo" @on-change="purchaseEdit($event)" :data="purchaseInfo" trigger="hover" filterable></Cascader>
				</FormItem>
				<FormItem label="供应商">
					<Input v-model="stockAddForm.supplierName" disabled></Input>
				</FormItem>
				<FormItem label="负责人">
					<Input disabled v-model="stockAddForm.purchaseName"></Input>
				</FormItem>
				<FormItem label="商品标签">
					<Input v-model="stockAddForm.commodityTagName" disabled></Input>
				</FormItem>
				<FormItem label="入库数量">
					<InputNumber disabled v-model="stockAddForm.stockCount" style="width: 100%;"></InputNumber>
				</FormItem>
				<FormItem label="商品单位">
					<Input disabled v-model="stockAddForm.commodityCompanyName"></Input>
				</FormItem>
				<FormItem label="入库备注">
					<Input v-model="stockAddForm.stockRemarks"></Input>
				</FormItem>
			</Form>
		</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				//库存备注、库存数量、
				//库存时间、 商品标签、商品单位
				// 供应商
				URL: "http://localhost:8888/",
				page_total: 0,
				stockId_del: 0,
				del_show: false,
				show_edit: false,
				modal_loading: false,
				page_hidden: true,
				loading: false,
				addStockModal: false,
				columns1: [{
						title: '进货表编号',
						key: 'purchaseId'
					},
					{
						title: "商品标签",
						key: "commodityTagName",
						align: 'center'
					},
					{
						title: '库存数量',
						key: 'stockCount',
						align: 'center'
					},
					{
						title: '商品单位',
						key: 'commodityCompanyName',
						align: 'center'
					},
					{
						title: "入库时间",
						key: "stockDatetime",
						align: 'center'
					},
					{
						title: "库存备注",
						key: "stockRemarks",
						tooltip: true
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
											this.stockEdit(params.row);
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
											this.remove(params.row.commodityId)
										}
									}
								}, '删除')
							]);
						}
					}
				],
				stockInfo: [],
				stockUpdateForm: {
					stockId: 0,
					stockCount: 0,
					stockRemarks: "",
					stockDatetime: "",
					purchaseId: 0,
					commodityTagId: 0,
					commodityTagName: "",
					commodityCompanyId: 0,
					commodityCompanyName: ''

				},
				stockAddForm: {
					stockCount: 0,
					stockRemarks: "",
					purchaseId: 0,
					commodityTagId: 0,
					commodityTagName: "",
					commodityCompanyId: 0,
					commodityCompanyName: '',
					supplierName: '',
					supplierId: 0,
					purchaseName: ''
				},
				stockAddInfo: [],
				purchaseInfo: []
			}
		},
		methods: {
			subAddStock() { //入库
				
				axios.post(this.URL + "stock",this.stockAddForm,{
					"Content-Type": "application/json"
				}).then(resp => {
					if( resp.data.code == 202 ){
						this.$Message.success(resp.data.data);
						this.changePage();
					}else{
						this.$Message.success(resp.data.data);
					}
				});
			},
			purchaseEdit(e) {
				if (e != "") {
					axios.get(this.URL + "purchase/" + e[0]).then(resp => {
						let temp = this.stockAddForm;
						let data = resp.data.data;
						temp.purchaseId = data.purchaseId;
						temp.commodityTagId = data.commodityTagId;
						temp.commodityTagName = data.commodityTag.commodityTagName;
						temp.commodityCompanyId = data.commodityCompanyId;
						temp.commodityCompanyName = data.commodityCompany.commodityCompanyName;
						temp.purchaseName = data.purchaseName;
						temp.supplierId = data.supplierId;
						temp.supplierName = data.supplier.supplierName;
						temp.stockCount = data.purchaseCount;
					});
				}
			},
			purchase_stock() { //入库下拉框
				axios.get(this.URL + "purchase/to").then(resp => {
					let data = [];
					for (let purchase of resp.data.data) {
						data.push({
							value: purchase.purchaseId,
							label: purchase.purchaseName,
							children: [{
								value: purchase.supplierId,
								label: purchase.supplier.supplierName
							}]
						});
					}
					this.purchaseInfo = data;
				});
			},
			addStock() { //入库
				this.addStockModal = true;
			},
			del() { //删除商品
				let that = this;
				this.$Message.error("对不起,此数据暂时无法删除！！！");
				this.modal_loading = false;
				this.del_show = false;
			},
			remove(index) { //删除商品
				this.stockId_del = index;
				this.del_show = true;
			},
			stockEdit(info) { //库存信息修改
				this.stockUpdateForm.stockId = info.stockId;
				this.stockUpdateForm.purchaseId = info.purchaseId;
				this.stockUpdateForm.stockCount = info.stockCount;
				this.stockUpdateForm.stockDatetime = info.stockDatetime;
				this.stockUpdateForm.commodityTagId = info.commodityTagId;
				this.stockUpdateForm.commodityTagName = info.commodityTagName;
				this.stockUpdateForm.commodityCompanyId = info.commodityCompanyId;
				this.stockUpdateForm.commodityCompanyName = info.commodityCompanyName;
				this.stockUpdateForm.stockRemarks = info.stockRemarks;
				this.show_edit = true;
			},
			btn_update() {
				axios.put(this.URL + "stock", this.stockUpdateForm, {
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
			},
			searchStock(e) { //搜索栏
				if (e.trim().length == 0) {
					this.changePage();
					return false;
				}
				axios.get(this.URL + "stock/search", {
					params: {
						searchText: e
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.stockInfo = [];
						if (resp.data.data != "") {

							//时间转换
							function takeTime(date) {
								let time = new Date(date);
								return time.getFullYear() + "-" + (time.getMonth() + 1) + "-" + time.getDate() + " " +
									time.getHours() + ":" + time.getMinutes() + ":" + time.getSeconds();
							};

							for (let re of resp.data.data) {
								this.stockInfo.push({
									stockId: re.stockId,
									stockCount: re.stockCount,
									stockRemarks: re.stockRemarks,
									stockDatetime: takeTime(re.stockDatetime),
									purchaseId: re.purchaseId,
									commodityTagId: re.commodityTagId,
									commodityTagName: re.commodityTag.commodityTagName,
									commodityCompanyId: re.commodityCompanyId,
									commodityCompanyName: re.commodityCompany.commodityCompanyName
								});
							}
						} else {
							this.stockInfo = [];
						}

						this.page_hidden = true;
					} else {
						this.$Message.success(resp.data.data);
						this.stockInfo = [];
						this.page_hidden = true;
					}
				});
			},
			changePage(ev) { //分页
				this.loading = true;
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
				axios.get(this.URL + "stock", {
					params: {
						page: page
					}
				}).then(resp => {
					for (let re of resp.data.data) {
						this.stockInfo.push({
							stockId: re.stockId,
							stockCount: re.stockCount,
							stockRemarks: re.stockRemarks,
							stockDatetime: takeTime(re.stockDatetime),
							purchaseId: re.purchaseId,
							commodityTagId: re.commodityTagId,
							commodityTagName: re.commodityTag.commodityTagName,
							commodityCompanyId: re.commodityCompanyId,
							commodityCompanyName:re.commodityCompany.commodityCompanyName,
						});
					}
					this.page_total = resp.data.total;
				});
			}
		},
		created() {
			this.changePage();
			this.purchase_stock();
			// 			this.stockInfo = [{
			// 				stockId: 1,
			// 				stockCount: 10,
			// 				stockRemarks: '测试数据',
			// 				stockDatetime: '2019-01-09 22:10:26',
			// 				purchaseId: 1,
			// 				purchaseName: '苹果供应商',
			// 				commodityTagId: 1,
			// 				commodityTagName: '苹果',
			// 				commodityCompanyId: 1,
			// 				commodityCompanyName: '箱'
			// 			}];
		}
	}
</script>
