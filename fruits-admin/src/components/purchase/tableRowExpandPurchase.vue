<template>
    <div>
		<span style="color: coral;">供应商信息</span>
		<Row class="expand-row" style="margin-top: 10px;margin-left: 20px;">
			<Col span="12">
				<span class="expand-value">供应商地址:{{row.supplierAddress}}</span>
			</Col>
			<Col span="12">
				<span class="expand-value">供应商联系电话:{{row.supplierTel}}</span>
			</Col>
		</Row>
		<span style="color: coral;">备注信息：</span>
        <Row class="expand-row" style="margin-top: 10px;margin-left: 20px;">
			
			<Col span="24">
				<span class="expand-value">{{ row.supplierRemarks }}</span>
			</Col>
        </Row>
    </div>
</template>
<script>
    export default {
        props: {
            row: Object
        }
    };
</script>
<style scoped>
    .expand-row{
        margin-bottom: 16px;
    }
</style>