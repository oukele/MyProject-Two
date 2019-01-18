<template>
	<div class="main">
		<div style="border: 2px solid #D7DDE4;width: 35%;height: 350px;float: left;margin-left:20px;margin-top: 20px;">
			<div style="height: auto;margin-bottom: 10px;">
				<Form label-position="left" :label-width="100">
					<FormItem label="库存商品">
						<Cascader v-model="stockCommodity" @on-change="stockDetail($event)" :data="stockArr" trigger="hover" filterable></Cascader>
					</FormItem>

					<Row style="background:#eee;padding:20px">
						<Col span="11">
						<Card>
							<p slot="title">商品库存数量</p>

							<p style="margin-left:40px;font-size: 20px;">{{stock.count}} {{stock.commodityCompany}}</p>

						</Card>
						</Col>
						<Col span="11" offset="2">
						<Card>
							<p style="text-align: center;" slot="title">商品标签</p>

							<p style="margin-left:40px;font-size: 20px;">{{stock.tagName}}</p>

						</Card>
						</Col>
					</Row>
				</Form>
			</div>
		</div>

		<div style="border: 1px solid gray;width: 35%;height: 350px;float: left;margin-left: 20px;margin-top:20px ;">

			<Form :model="formData" label-position="left" :label-width="100">
				<FormItem label="商品简介">
					<Input v-model="formData.commodityName" placeholder="请输入商品简介"></Input>
				</FormItem>
				<FormItem label="商品价格">
					<InputNumber style="width: 100%;" :max="1000000" :min="0" v-model="formData.commodityPrice" placeholder="请输入商品价格"></InputNumber>
				</FormItem>
				<FormItem label="商品数量">
					<InputNumber style="width: 100%;" v-model="formData.commodityCount" :max="10000" :min="1" placeholder="请输入数量"></InputNumber>
				</FormItem>

				<FormItem label="商品单位">
					<Select v-model="stock.commodityCompany" disabled style="width:90%">
						<Option :value="stock.commodityCompany">{{stock.commodityCompany }}</Option>
					</Select>
				</FormItem>

				<FormItem label="商品图片">

					<Upload :on-success='ImgUpload' name="filename" action="http://localhost:8888/fileUpload">
						<Button style="margin-left: 50px;" icon="ios-cloud-upload-outline">点击选择图片</Button>
					</Upload>

				</FormItem>
			</Form>
		</div>

		<div style="border: 2px solid lightgreen;width: 180px;height:180px;float: left;margin-left: 30px;margin-top:20px ;">
			<div>
				<img v-if=" formData.imgUrl != '' " :src="URL + formData.imgUrl" alt="出现错误,请联系管理员....." height="180px" width="180px">
			</div>
		</div>
		
		<div style="float: left;width: 25%;margin-left: 350px;margin-top: 10px;">
			
			<Button @click="submit" type="primary">添加</Button>
			<Button @click="handleReset" style="margin-left: 8px">重置</Button>
			
		</div>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				URL: "http://localhost:8888/",
				formData: {
					commodityName: '',
					commodityPrice: 0,
					commodityCount: 0.0,
					commodityCompany: "",
					commodityCompanyId:0,
					stockId:0,
					imgUrl: ''
				},
				model1: '',
				stockArr: [],
				stockCommodity: [],
				stock: {
					count: "",
					tagName: "暂无",
					commodityCompany: 0
				}

			}
		},
		methods: {
			ImgUpload(resp, file) {
				if (resp.code == 200) {
					this.formData.imgUrl = "images/" + resp.msg;
				}
			},
			stockDetail(value) {
				if (value.length != 0) {
					axios.get(this.URL + "stock/" + value[1]).then(resp => {
						this.stock.tagName = resp.data.data.commodityTag.commodityTagName;
						this.stock.count = resp.data.data.stockCount;
						this.stock.commodityCompany = resp.data.data.commodityCompany.commodityCompanyName;
						
						this.formData.commodityCompany = resp.data.data.commodityCompany.commodityCompanyName;
						
						this.formData.commodityCompanyId = resp.data.data.commodityCompany.commodityCompanyId;
					});
					this.formData.stockId = value[1];
				}
			},
			stockInfo() {
				axios.get(this.URL + "stock").then(resp => {
					let data = [];
					for (let stock of resp.data.data) {
						data.push({
							value: stock.commodityTagId,
							label: stock.commodityTag.commodityTagName,
							children: [{
								value: stock.stockId,
								label: stock.stockRemarks
							}]
						});
					}
					this.stockArr = data;
				});
			},
			handleReset() {
				this.formData.commodityCount = 0;
				this.formData.commodityName = "";
				this.formData.commodityPrice = 0;
				this.imgUrl = "";
				this.stockCommodity = [];
				this.stock.tagName = "暂无";
				this.stock.count = "";
				this.stock.commodityCompany = 0;
				this.formData.imgUrl = "";
			},
			submit() {
				if (this.stockCommodity.length == 0) {
					return alert("请选择 库存中的商品 然后在进行商品上架。。");
				}
				
				let flag = this.Verification(this.formData);
				
				if( flag == false )
					return false;
				
				
				if (this.formData.imgUrl == "") {
					if (confirm("商品图片未上传,是否要上传图片？")) {
						return false;
					}
				}
				
				axios.post(this.URL + "commodity", this.formDataSubmit(this.formData), {
					headers: {
						"Content-Type": "application/json;charset=utf-8"
					}
				}).then(resp => {
					if( resp.data.code === 202 ){
						this.$Message.success(resp.data.data);
					}else{
						alert(resp.data.data);
					}
					this.handleReset();//清空
				});
				
			},
			Verification(form) {

				if (form.commodityName == "") {
					alert("请填写商品简介...")
					return false;
				}

				if (form.commodityPrice == 0) {
					alert("请填写商品价格.....")
					return false;
				}

				if (form.commodityCount == 0) {
					alert("请填写商品上架数量......")
					return false;
				}

				if (form.commodityCompany == "") {
					alert("请选择 库存中的商品..")
					return false;
				}
				

			},
			formDataSubmit(form) {
				let data1 = {
					commodityName: form.commodityName,
					commodityPrice: form.commodityPrice,
					commodityCount: form.commodityCount,
					stockId:form.stockId,
					commodityCompanyId:form.commodityCompanyId,
					commodityPhoto: form.imgUrl
				};
				return data1;
			}
		},
		created() {
			this.stockInfo();
		}
	}
</script>
<style>
	.main {
		border: 3px solid beige;
		width: 100%;
		margin-top: 5px;
		height: 430px;
		margin-left: 10px;
	}

	.demo-upload-list {
		display: inline-block;
		width: 60px;
		height: 60px;
		text-align: center;
		line-height: 60px;
		border: 1px solid transparent;
		border-radius: 4px;
		overflow: hidden;
		background: #fff;
		position: relative;
		box-shadow: 0 1px 1px rgba(0, 0, 0, .2);
		margin-right: 4px;
	}

	.demo-upload-list img {
		width: 100%;
		height: 100%;
	}

	.demo-upload-list-cover {
		display: none;
		position: absolute;
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		background: rgba(0, 0, 0, .6);
	}

	.demo-upload-list:hover .demo-upload-list-cover {
		display: block;
	}

	.demo-upload-list-cover i {
		color: #fff;
		font-size: 20px;
		cursor: pointer;
		margin: 0 2px;
	}
</style>
