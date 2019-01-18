<template>
    <div>
        <Row class="expand-row">
			<span style="color: coral;">备注信息：</span>
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