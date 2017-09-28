<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout" layout:decorate="~{layouts/layout}">
<#include "fragments/head.html">
<body>
<#include "fragments/navbar.html">
    <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="jumbotron">
                <h1><img src="images/eeminder.png"/>Sqrambler</h1>
                <h2>Prototype Web Site</h2>
                <p>This is a prototype Sqrambler/media processing site. Authentication required.</p>
            </div>
            <div class="row">
                <div class="col-xs-6 col-lg-4">
                    <h2>Create Sqrambler Project</h2>
                    <p>Create a new Sqrambler project using uploaded images.</p>
                    <p><a class="btn btn-default" href="create.html" role="button" target = "_blank">Create &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->
                <div class="col-xs-6 col-lg-4">

                    <h2>Manage a Sqrambler Project</h2>
                    <p>Create a previously created Sqrambler project</p>
                    <p><a class="btn btn-default" href="control.html" role="button" target="_blank" >Manage &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->

                <div class="col-xs-6 col-lg-4">
                    <h2>Join a Sqrambler Project</h2>
                    <p>Join a Sqrambler Project just created or already in progress</p>
                    <p><a class="btn btn-default" href="device.html" role="button" target = "_blank">Join &raquo;</a></p>
                </div><!--/.col-xs-6.col-lg-4-->

            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <br/>
        <br/>
        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a target="_blank" href="loadimages.html" class="list-group-item">Manage Media</a>
                <a target="_blank" href="deleteproject.html" class="list-group-item">Delete Project</a>
                <a target="_blank" href="deviceinfo.html" class="list-group-item">Device Info</a>
                <a target="_blank" href="device.html" class="list-group-item">Join</a>
                <a target="_blank" href="querycode.html" class="list-group-item">Query Bar Code</a>
                <a target="_blank" href="sessions.html" class="list-group-item">Active Sessions</a>
                <a target="_blank" href="https://bfaul@bitbucket.org/TeamMosaic/mosaic-v1.git/src" class="list-group-item">View Source Code</a>
                <a target="_blank" href="javadoc/index.html" class="list-group-item">Javadoc</a>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->
<#include "fragments/footer.html">
</body>
</html>
