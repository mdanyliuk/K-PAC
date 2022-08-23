<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>K-pac application</title>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/codebase/grid.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/codebase/grid.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/codebase/index.css">
</head>
<body>
    <header class="dhx_sample-header">
    	<div class="dhx_sample-header__main">
    		<nav class="dhx_sample-header__breadcrumbs">
            	<ul class="dhx_sample-header-breadcrumbs">
            		<li class="dhx_sample-header-breadcrumbs__item">
            			<a href="/kpac-app/newset" class="dhx_sample-header-breadcrumbs__link">Add new set</a>
            		</li>
            	</ul>
            </nav>
    		<h1 class="dhx_sample-header__title">
    			<div class="dhx_sample-header__content">
    				K - pacs
    			</div>
    		</h1>
    		<nav class="dhx_sample-header__breadcrumbs">
            <ul class="dhx_sample-header-breadcrumbs">
             		<li class="dhx_sample-header-breadcrumbs__item">
              			<a href="/kpac-app/kpacs" class="dhx_sample-header-breadcrumbs__link">K-pacs</a>
             		</li>
         	  </ul>
        </nav>
    	</div>
    </header>
    <section class="dhx_sample-container" style="height: 80%">
    	<div style="height: 100%; width: 100%" id="grid"></div>
    </section>
    <script>
        const grid = new dhx.Grid("grid", {
    		columns: [
    			{ width: 100, id: "id", header: [{ text: "Id" }, { content: "inputFilter" }] },
    			{ width: 200, id: "title", header: [{ text: "Title" }, { content: "inputFilter" }] },
    			{ width: 100, id: "action", header: [{ text: "Delete" }],
              htmlEnable: true, align: "center",
              template: function () {
                  return "<span class='action-buttons'><a class='remove-button'>Delete</a></span>"
              } },
    		],
    		autoWidth: true,
    		data: ${sets},
    		eventHandlers: {
                onclick: {
                    "remove-button": function (e, data) {
                        var rowId = data.row.id;
                        fetch('/kpac-app/set/' + rowId, {
                            method: 'delete'
                        }).then(function (data){

                        });
                        grid.data.remove(rowId);
                    }
                }
            }
    	});
    </script>
    <style>
      	body {
            margin: 0;
        }
    	.action-buttons {
    		display: flex;
    		justify-content: space-evenly;
          	width: 100%;
    	}
    	.remove-button {
    		cursor: pointer;
    		color: #ff0000;
    	}
    </style>
</body>
</html>