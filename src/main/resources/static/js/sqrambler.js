function doModal(title,text) {
    var etype;
    if (title === "Error")
    	etype = "type-danger";
    else
    	etype = "type-info";
    	
	BootstrapDialog.show({
    	type: etype,
        title: title,
        message: text
    });
}

function toggle(elementId) {
	var ele = document.getElementById(elementId);
    if (ele.style.display == "block") {
        ele.style.display = "none";
    } else {
      ele.style.display = "block";
    }
}