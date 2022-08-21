<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring web application</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/codebase/grid.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/codebase/grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/codebase/index.css">
</head>
<body>
    <header class="dhx_sample-header">
    		<div class="dhx_sample-header__main">
    			<h1 class="dhx_sample-header__title">
    				<div class="dhx_sample-header__content">
    					K - pacs
    				</div>
    			</h1>
    		</div>
    </header>
    <section class="dhx_sample-container" style="height: 80%">
    	<div style="height: 100%; width: 100%" id="grid"></div>
    </section>
    <script>
        const grid = new dhx.Grid("grid", {
    		columns: [
    			{ width: 100, id: "id", header: [{ text: "Id" }] },
    			{ width: 300, id: "title", header: [{ text: "Title" }] },
    			{ width: 600, id: "description", header: [{ text: "Description" }] },
    			{ width: 150, id: "creationDate", header: [{ text: "Creation Date" }] }
    		],
    		data: ${kpacs}
    	});
    </script>
</body>
</html>