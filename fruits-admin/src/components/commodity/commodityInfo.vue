<template>
	<div>
		<Table :loading="loading" size="large" height="400" :columns="columns1" :data="commodityInfo" stripe></Table>
		<div style="margin: 10px;overflow: hidden">
			<div style="float: right;">
				<Page :total="data_total" :current="1" @on-change="changePage($event)"></Page>
			</div>
		</div>

		<!-- 修改弹出层 -->
		<Modal v-model="show_edit" @on-ok="btn_update" title="编辑商品信息" width="600">
			<Form :model="commodityUpdateForm" label-position="left" :label-width="100">
				<FormItem label="商品简介">
					<Input v-model="commodityUpdateForm.commodityName" placeholder="请填写商品的简介"></Input>
				</FormItem>
				<FormItem label="商品价格">
					<InputNumber v-model="commodityUpdateForm.commodityPrice" style="width: 100%;" :max="100000" :min="0.01"
					 placeholder="请输入商品的价格"></InputNumber>
				</FormItem>
				<FormItem label="商品数量">
					<InputNumber v-model="commodityUpdateForm.commodityCount" style="width: 100%;" :min="1" placeholder="请输入商品的数量"></InputNumber>
				</FormItem>
				<FormItem label="商品单位">
					<Input disabled v-model="commodityUpdateForm.commodityCompany.commodityCompanyName"></Input>
				</FormItem>
				<Row :gutter="16">
					<Col span="4">商品图片</Col>

					<Col span="8">
					<Upload :on-success='ImgUpload_update' name="filename" action="http://localhost:8888/fileUpload">
						<Button icon="ios-cloud-upload-outline">点击更换商品图片</Button>
					</Upload>
					</Col>
					<Col span="8">
					<div style="border-radius:50%;width: 120px;height: 120px;">
						<img v-if="commodityUpdateForm.commodityPhoto !=''" width="120px" height="120px" :src="URL +commodityUpdateForm.commodityPhoto"
						 alt="出现未知错误!">
					</div>
					</Col>

					<Col span="4">&ensp;</Col>
				</Row>
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
	export default {
		data() {
			return {
				URL: "http://localhost:8888/",
				data_total: 0,
				commodity_id_del: 0,
				del_show: false,
				show_edit: false,
				modal_loading: false,
				loading: true,
				columns1: [{
						title: '商品简介',
						key: 'commodityName',
						tooltip: true
					},
					{
						title: "商品图片",
						key: "commodityPhoto",
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
						title: '商品价格',
						key: 'commodityPrice',
						align: 'center'
					},
					{
						title: '商品数量',
						key: 'commodityCount',
						align: 'center'
					},
					{
						title: "商品单位",
						key: "commodityCompanyName",
						align: 'center'
					},
					{
						title: "商品状态",
						key: "commodityStatus",
						render: (h, params) => {
							//return h('元素',{元素的性质},'内容')
							return h('i-switch', {
								props: { //设置属性
									size: 'large',
									value: params.row.commodityStatus //通过value控制switch开关 （true,false）
								},
								scopedSlots: {
									open: () => h('span', '上架'),
									close: () => h('span', '下架')
								},
								on: {
									'on-change': (value) => {
										//修改状态,参数为 商品id,状态值
										this.changeStatus(params.row.commodityId, value);
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
											this.commodityEdit(params.row);
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
				commodityInfo: [],
				commodityUpdateForm: {
					commodityId:0,
					stockId:0,
					commodityCompanyId:1,//无用默认数据，便于通过服务器的验证
					commodityName: '',
					commodityPrice: 0,
					commodityCount: 0,
					commodityPhoto: '',
					commodityCompany: {
						commodityCompanyName: ''
					}
				}
			}
		},
		methods: {
			del() { //删除商品
				let that = this;
				if (this.commodity_id_del > 0) {
					this.modal_loading = true; //加载圈

					axios.delete(this.URL + "commodity/" + this.commodity_id_del).then(resp => {
						console.log(resp.data.code);
						if (resp.data.code == 202) {
							this.commodityInfo = this.commodityInfo.filter((e) => {
								return e.commodityId != that.commodity_id_del
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
			changeStatus(id, status) { //修改状态
				this.loading = true; //显示加载中....
				axios.put("http://localhost:8888/commodity/" + id + "/" + status + "").then(resp => {
					if (resp.data.code == "202") {
						this.$Message.success('状态修改成功');
					} else {
						alert("出现异常,请联系管理员。");
					}
					this.loading = false; //关闭加载中...
				});
			},
			commodityEdit(info) { //商品信息修改
				this.commodityUpdateForm.commodityId = info.commodityId;
				this.commodityUpdateForm.stockId = info.stockId;
				this.commodityUpdateForm.commodityName = info.commodityName;
				this.commodityUpdateForm.commodityCompany.commodityCompanyName = info.commodityCompanyName;
				this.commodityUpdateForm.commodityCount = info.commodityCount;
				this.commodityUpdateForm.commodityPhoto = info.commodityPhoto;
				this.commodityUpdateForm.commodityPrice = info.commodityPrice;
				this.show_edit = true;
				// 				this.$Modal.info({
				// 					title: 'User Info',
				// 					content: `Name：${this.commodityInfo[index].commodityName}`
				// 				})
			},
			btn_update() {
				axios.put(this.URL+"commodity", this.commodityUpdateForm, {
					"Content-Type": "application/json"
				}).then(resp => {
					if( resp.data.code == 202 ){
						this.$Message.success(resp.data.data);
					}else{
						this.$Message.success(resp.data.data);
					}
				});
				//刷新页面数据
				this.changePage();
			},
			ImgUpload_update(resp, file) {
				if (resp.code == 200) {
					this.commodityUpdateForm.commodityPhoto = "images/" + resp.msg;
				}
			},
			remove(index) { //删除商品
				this.commodity_id_del = index;
				this.del_show = true;
			},
			changePage(ev) { //分页
				this.loading = true;
				this.dataTemple(ev);
				this.loading = false;
			},
			//商品数据模板
			dataTemple(page) {
				this.commodityInfo = []; //数组清空
				axios.get("http://localhost:8888/commodity", {
					params: {
						page: page
					}
				}).then(resp => {
					for (let re of resp.data.data) {
						this.commodityInfo.push({
							commodityId: re.commodityId,
							commodityName: re.commodityName,
							commodityPhoto: re.commodityPhoto,
							commodityPrice: re.commodityPrice,
							commodityCount: re.commodityCount,
							commodityStatus: re.commodityStatus,
							stockId: re.stockId,
							commodityCompanyId: re.commodityCompanyId,
							commodityCompanyName: re.commodityCompany.commodityCompanyName
						});
					}
				});
				axios.get("http://localhost:8888/commodity/total").then(resp => {
					this.data_total = resp.data.data;
				});
			}
		},
		created() {
			this.changePage();
		}
	}
</script>
