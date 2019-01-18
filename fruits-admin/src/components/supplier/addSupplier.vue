<template>
	<div style="margin: auto;width: 50%;">
		<Form :model="supplierAddForm" label-position="left" :label-width="100">
			<FormItem label="供应商名称">
				<Input v-model="supplierAddForm.supplierName" placeholder="请填写供应商名称...."></Input>
			</FormItem>
			<FormItem label="供应商地址">
				<Input v-model="supplierAddForm.supplierAddress" placeholder="请填写供应商地址...."></Input>
			</FormItem>
			<FormItem label="供应商电话">
				<Input v-model="supplierAddForm.supplierTel" placeholder="请填写供应商电话...."></Input>
			</FormItem>
			<FormItem label="供应商备注">
				<Input v-model="supplierAddForm.supplierRemarks" type="textarea" :autosize="{minRows: 4,maxRows: 6}" placeholder="请输入供应商备注...."></Input>
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
				supplierAddForm: {
					supplierName: '',
					supplierAddress: '',
					supplierTel: '',
					supplierRemarks: ''
				}
			}
		},
		methods: {
			submit() {
				let flag = this.Verification(this.supplierAddForm);
				if (flag == false)
					return false;
				axios.post(this.URL + "supplier", this.supplierAddForm, {
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
				this.supplierAddForm.supplierName = '';
				this.supplierAddForm.supplierAddress = '';
				this.supplierAddForm.supplierTel = '';
				this.supplierAddForm.supplierRemarks = '';
			},
			Verification(form) {

				if (form.supplierName == "") {
					this.$Message.error("请填写供应商名称.....");
					return false;
				}

				if (form.supplierAddress == '') {
					this.$Message.error("请填写供应商地址.....");
					return false;
				}
				if (form.supplierTel == '') {
					this.$Message.error("请填写供应商联系电话.....");
					return false;
				}
				let reg = /^[0-9]{11}$/g;
				if (reg.test(form.supplierTel) == false) {
					this.$Message.error("填写的供应商联系电话格式不正确.....");
					return false;
				}

			}
		}
	}
</script>

<style>

</style>
