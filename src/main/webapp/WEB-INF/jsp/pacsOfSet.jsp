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
            			<a href="/kpac-app/newpac" class="dhx_sample-header-breadcrumbs__link">Add new K-pac</a>
            		</li>
            	</ul>
            </nav>
    		<h1 class="dhx_sample-header__title">
    			<div class="dhx_sample-header__content">
    				Set ${setName}
    			</div>
    		</h1>
    		<nav class="dhx_sample-header__breadcrumbs">
        	<ul class="dhx_sample-header-breadcrumbs">
         		<li class="dhx_sample-header-breadcrumbs__item">
         			<a href="/kpac-app/sets" class="dhx_sample-header-breadcrumbs__link">K-pac sets</a>
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
    			{ width: 500, id: "description", header: [{ text: "Description" }, { content: "inputFilter" }] },

                { width: 100, id: "action", header: [{ text: "Delete" }],
                    htmlEnable: true, align: "center",
                    template: function () {
                        return "<span class='action-buttons'><a class='remove-button'>Delete</a></span>"
                    } },
    		    ],
    		autoWidth: true,
    		data: ${kpacs},
    		eventHandlers: {
                onclick: {
                    "remove-button": function (e, data) {
                        var rowId = data.row.id;
                        fetch('/kpac-app/kpac/' + rowId, {
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