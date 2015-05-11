<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/tags.jsp"%>
<div class="tabbable tabbable-primary">
	<ul class="nav nav-pills">
		<li class="active"><a class="tab-default" data-toggle="tab" href="#tab-auto">列表查询</a></li>
		<li class="tools pull-right"><a class="btn default reload" href="javascript:;"><i class="fa fa-refresh"></i></a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane fade active in">
			<div class="row search-form-default engineering-info-search-div">
				<div class="col-md-12">
					<form action="#" method="get" class="form-inline form-validation form-search-init"
						data-grid-search=".grid-project-engineering-engineering-info-index">
						<div class="form-group">
							<input type="text" id="engi_info_search_engineeringCode" name="search['EQ_contract.engineeringCode']" class="form-control" placeholder="工程编号">
						</div>
						<div class="form-group">
							<input type="text" name="search['CN_contract.engineeringName']" class="form-control" placeholder="工程名称">
						</div>
						<button class="btn green" type="submmit">
							<i class="m-icon-swapright m-icon-white"></i>&nbsp; 查&nbsp;询
						</button>
						<button class="btn default hidden-inline-xs" type="reset">
							<i class="fa fa-undo"></i>&nbsp; 重&nbsp;置
						</button>
					</form>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<table class="grid-project-engineering-engineering-info-index"></table>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${ctx}/resources/script/project/engineering/engineering-info-index.js" />
<%@ include file="/WEB-INF/common/ajax-footer.jsp"%>