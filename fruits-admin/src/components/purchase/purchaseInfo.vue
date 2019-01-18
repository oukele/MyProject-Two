<template>
	<div>
		<Row>
			<Col span="2">&nbsp;</Col>
			<Col span="8">
			<Input search enter-button  @on-search='searchPurchase'  placeholder="请输入关键字..." />
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
				<Table :loading="loading" :columns="columns10" height="380" style="margin-top: 6px;" :data="purchaseInfo"></Table>
			</template>
			<div style="float: right;" :hidden=page_hidden>
				<Page :total="page_total" :current="1" @on-change="changePage($event)"></Page>
			</div>

			</Col>
		</Row>

		<!-- 修改弹出层 -->
		<Modal v-model="show_edit" @on-ok="btn_update" title="编辑进货信息" width="600">
			<Form :model="purchaseUpdateForm" label-position="left" :label-width="100">
				<FormItem label="供应商名称">
					<div v-if="selectSupplierNameList.length >0 ">
						<Select v-model="purchaseUpdateForm.supplierId" clearable style="width:100%">
							<Option v-for="item in selectSupplierNameList" :value="item.value" :key="item.value">{{ item.label }}</Option>
						</Select>
					</div>
					<div v-else>
						<Input disabled="disabled" v-model="selectSupplierName" placeholder="请填写供应商名称...."></Input>
					</div>
				</FormItem>
				<FormItem label="负责人">
					<Input v-model="purchaseUpdateForm.purchaseName" placeholder="请填写负责人...."></Input>
				</FormItem>
				<FormItem label="进货数量">
					<InputNumber style="width:100%" v-model="purchaseUpdateForm.purchaseCount" :min="0" :max="100000" placeholder="请填写进货数量...."></InputNumber>
				</FormItem>
				<FormItem label="商品标签">
					<Select v-model="purchaseUpdateForm.commodityTagId" clearable style="width:100%">
						<Option v-for="item in selectCommodityTagList" :value="item.value" :key="item.value">{{ item.label }}</Option>
					</Select>
				</FormItem>
				<FormItem label="商品单位">
					<Select v-model="purchaseUpdateForm.commodityCompanyId" clearable style="width:100%">
						<Option v-for="item in selectcommodityCompanyList" :value="item.value" :key="item.value">{{ item.label }}</Option>
					</Select>
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
	import expandRow from './tableRowExpandPurchase.vue'
	export default {
		components: {
			expandRow
		},
		methods: {
			data_template(r) { //数据模板
				this.purchaseInfo = []; //清空
				let arr = [];
				for (let rs of r) {
					arr.push({
						purchaseId: rs.purchaseId,
						purchaseName: rs.purchaseName,
						purchaseCount: rs.purchaseCount,
						purchaseStatus: rs.purchaseStatus,
						commodityCompanyId: rs.commodityCompany.commodityCompanyId,
						commodityCompanyName: rs.commodityCompany.commodityCompanyName,
						commodityTagId:rs.commodityTagId,
						commodityTagName: rs.commodityTag.commodityTagName,
						supplierId: rs.supplier.supplierId,
						supplierName: rs.supplier.supplierName,
						supplierAddress: rs.supplier.supplierAddress,
						supplierRemarks: rs.supplier.supplierRemarks,
						supplierTel: rs.supplier.supplierTel
					});
				}
				return arr;
			},
			changePageSearch(page) { //模糊查询的分页
				axios.get(this.URL + "purchase/searchPurchase", {
					params: {
						searchName: this.searchName,
						page: page
					}
				}).then(resp => {
					this.purchaseInfo = this.data_template(resp.data.data);
					this.loading = false;
				});
			},
			mainData(page) { //主要数据
				axios.get(this.URL + "/purchase", {
					params: {
						page: page
					}
				}).then(resp => {
					this.purchaseInfo = this.data_template(resp.data.data);
					this.loading = false;
				});
				axios.get(this.URL + "/purchase/getTotal").then(resp => {
					this.page_total = resp.data.data;
				});

				//查询状态 正常的供应商
				axios.get(this.URL + "supplier/supplierStatus", {
					params: {
						supplierStatus: 1
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.selectSupplierNameList = [];
						for (let rs of resp.data.data) {
							this.selectSupplierNameList.push({
								label: rs.supplierName,
								value: rs.supplierId
							});
						}
					}
				});
				//查询 商品单位
				axios.get(this.URL + "commodityCompany").then(resp => {
					if (resp.data.code == 202) {
						this.selectcommodityCompanyList = [];
						for (let rs of resp.data.data) {
							this.selectcommodityCompanyList.push({
								label: rs.commodityCompanyName,
								value: rs.commodityCompanyId
							});
						}
					}
				});
				//查询商品标签
				axios.get(this.URL+"commodityTag").then(resp=>{
					if (resp.data.code == 202) {
						this.selectCommodityTagList = [];
						for (let rs of resp.data.data) {
							this.selectCommodityTagList.push({
								label: rs.commodityTagName,
								value: rs.commodityTagId
							});
						}
					}
				});
			},
			changePage(page) { //分页
				this.btnSupplierStatus = "全部";
				if (this.searchName == '') {
					this.mainData(page);
				} else {
					this.changePageSearch(page);
				}
				this.page_hidden = false;
			},
			searchPurchase(e) { //按照 负责人 供应商 商品标签 进行 搜索
				if (e == '') {
					this.changePage();
				}
				// 模糊 查询后 的数据 
				axios.get(this.URL + "purchase/searchTotal", {
					params: {
						searchName: e
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.page_total = resp.data.data;
					}
				});
				// 模糊查询 得到的数据
				this.searchName = e;
				axios.get(this.URL + "purchase/searchPurchase", {
					params: {
						searchName: e
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.purchaseInfo = this.data_template(resp.data.data);
					} else {
						this.$Message.success(resp.data.data);
						this.purchaseInfo = [];
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
				axios.get(this.URL + "purchase/purchaseStatus", {
					params: {
						purchaseStatus: condition
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.purchaseInfo = this.data_template( resp.data.data );
						this.page_hidden = true;
					} else {
						this.$Message.success(resp.data.data);
						this.purchaseInfo = [];
						this.page_hidden = true;
					}
				});
			},
			purchaseEdit(row) { //编辑 进货 信息
				this.show_edit = true;

				this.purchaseUpdateForm.purchaseId = row.purchaseId; //进货Id
				this.purchaseUpdateForm.supplierId = row.supplierId; //供应商Id
				this.selectSupplierName = row.supplierName; //供应商名称
				this.purchaseUpdateForm.commodityTagId = row.commodityTagId;
				this.purchaseUpdateForm.purchaseName = row.purchaseName; //负责人
				this.purchaseUpdateForm.purchaseCount = row.purchaseCount; //进货数量
				this.purchaseUpdateForm.commodityCompanyId = row.commodityCompanyId; //商品单位
			},
			changeStatus(id, status) { //修改进货状态
				this.loading = true; //显示加载中....
				axios.put(this.URL + "purchase/" + id + "/" + status + "").then(resp => {
					if (resp.data.code == "202") {
						this.$Message.success(resp.data.data);
					} else {
						alert("出现异常,请联系管理员。");
					}
					this.loading = false; //关闭加载中...
				});
			},
			remove(id) { //删除 进货信息
				this.purchaseId = id;
				this.del_show = true;
			},
			del() { //删除 进货信息
				let that = this;
				if (this.purchaseId > 0) {
					this.modal_loading = true; //加载圈

					axios.delete(this.URL + "purchase/" + this.purchaseId).then(resp => {

						if (resp.data.code == 202) {
							this.purchaseInfo = this.purchaseInfo.filter((e) => {
								return e.purchaseId != that.purchaseId
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
				axios.put(this.URL + "purchase", this.purchaseUpdateForm, {
					"Content-Type": "application/json"
				}).then(resp => {
					if (resp.data.code == 202) {
						this.$Message.success(resp.data.data);
					} else {
						this.$Message.error(resp.data.data);
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
				purchaseId: 0,
				page_total: 0,
				searchName: '', //模糊查询的关键字
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
						key: 'supplierName',
						align: 'center',
						tooltip: true
					},
					{
						title: '负责人',
						key: 'purchaseName',
						align: 'center'
					},
					{
						title: '商品标签',
						key: 'commodityTagName',
						align: 'center'
					},
					{
						title: '进货数量',
						key: 'purchaseCount',
						align: 'center'
					},
					{
						title: '商品单位',
						key: 'commodityCompanyName',
						align: 'center'
					},
					{
						title: '进货状态',
						key: 'purchaseStatus',
						render: (h, params) => {
							//return h('元素',{元素的性质},'内容')
							return h('i-switch', {
								props: { //设置属性
									size: 'large',
									value: params.row.purchaseStatus //通过value控制switch开关 （true,false）
								},
								scopedSlots: {
									open: () => h('span', '正常'),
									close: () => h('span', '异常')
								},
								on: {
									'on-change': (value) => {
										//修改状态,参数为 进货id,状态值
										this.changeStatus(params.row.purchaseId, value);
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
											this.purchaseEdit(params.row);
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
											this.remove(params.row.purchaseId)
										}
									}
								}, '删除')
							]);
						}
					}
				],
				purchaseInfo: [],
				purchaseUpdateForm: {
					purchaseId: 0,
					purchaseName: '',
					purchaseCount: 0,
					supplierId: 0,
					commodityCompanyId: 0,
					commodityTagId:0
				},
				selectSupplierNameList: [],
				selectSupplierName: '',
				selectcommodityCompanyList: [],
				selectcommodityCompanyName: '',
				selectCommodityTagList:[]
			}
		}
	}
</script>

<style>
	.expand-row {
		margin-bottom: 16px;
	}
</style>
