<template>
	<div style="margin: auto;width: 50%;">
		<Form :model="purchaseAddForm" label-position="left" :label-width="100">
			<FormItem label="供应商名称">
				<div v-if="selectSupplierNameList.length >0 ">
					<Select v-model="purchaseAddForm.supplierId" clearable style="width:100%">
						<Option v-for="item in selectSupplierNameList" :value="item.value" :key="item.value">{{ item.label }}</Option>
					</Select>
				</div>
				<div v-else>
					<Input disabled="disabled" placeholder="没有可选的供应商..."></Input>
				</div>
			</FormItem>
			<FormItem label="负责人">
				<Input v-model="purchaseAddForm.purchaseName" placeholder="请填写相关的负责人...."></Input>
			</FormItem>
			<FormItem label="进货数量">
				<InputNumber style="width:100%" :min="0" :max="100000000"  v-model="purchaseAddForm.purchaseCount" placeholder="请填写进货数量...."></InputNumber>
			</FormItem>
			<FormItem label="商品标签">
				<div v-if="selectCommodityTagList.length >0 ">
					<Select v-model="purchaseAddForm.commodityTagId" clearable style="width:100%">
						<Option v-for="item in selectCommodityTagList" :value="item.value" :key="item.value">{{ item.label }}</Option>
					</Select>
				</div>
				<div v-else>
					<Input disabled="disabled" placeholder="没有可选的商品标签..."></Input>
				</div>
			</FormItem>
			<FormItem label="商品单位">
				<div v-if="selectCommodityCompanyList.length >0 ">
					<Select v-model="purchaseAddForm.commodityCompanyId" clearable style="width:100%">
						<Option v-for="item in selectCommodityCompanyList" :value="item.value" :key="item.value">{{ item.label }}</Option>
					</Select>
				</div>
				<div v-else>
					<Input disabled="disabled" placeholder="没有可选的商品单位..."></Input>
				</div>
			</FormItem>
		</Form>
		<div style="float: left;width: 25%;margin-left: 260px;margin-top: 10px;">
			<Button @click="submit" type="primary">添加</Button>
			<Button @click="handleReset" style="margin-left: 8px">重置</Button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				URL: 'http://localhost:8888/',
				purchaseAddForm: {
					supplierId:0,
					purchaseName: '',
					purchaseCount: 0,
					commodityCompanyId:0,
					commodityTagId:0
				},
				selectSupplierNameList:[],
				selectCommodityTagList:[],
				selectCommodityCompanyList:[]
			}
		},
		methods: {
			selectList(){
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
				//查询商品单位
				axios.get(this.URL + "commodityCompany").then(resp => {
					if (resp.data.code == 202) {
						this.selectCommodityCompanyList = [];
						for (let rs of resp.data.data) {
							this.selectCommodityCompanyList.push({
								label: rs.commodityCompanyName,
								value: rs.commodityCompanyId
							});
						}
					}
				});
			},
			submit() {
				let flag = this.Verification(this.purchaseAddForm);
				if (flag == false)
					return false;
				axios.post(this.URL + "purchase", this.purchaseAddForm, {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(resp => {
					if (resp.data.code == 202) {
						this.$Message.success(resp.data.data);
						this.handleReset(); //清空表单
					} else {
						alert('出现错误,请联系管理员。');
					}
				});

			},
			handleReset() {
				this.purchaseAddForm.supplierId = 0;
				this.purchaseAddForm.purchaseName = '';
				this.purchaseAddForm.commodityCompanyId = 0;
				this.purchaseAddForm.commodityTagId = 0;
				this.purchaseAddForm.purchaseCount = 0;
			},
			Verification(form) {

				if (form.supplierId == 0) {
					this.$Message.error("请选择供应商.....");
					return false;
				}

				if (form.purchaseName == '') {
					this.$Message.error("请填写相关负责人.....");
					return false;
				}
				if (form.purchaseCount == 0) {
					this.$Message.error("进货的数量不能少于0.....");
					return false;
				}
				if (form.commodityCompanyId == 0) {
					this.$Message.error("请选择商品单位.....");
					return false;
				}
				if (form.commodityTagId == 0) {
					this.$Message.error("请选择商品标签.....");
					return false;
				}

			}
		},
		created() {
			this.selectList();
		}
	}
</script>

<style>

</style>
