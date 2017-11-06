function uploadFile() 
{

    var xhr = new XMLHttpRequest();       

    xhr.open("POST","DropBoxServlet", true);

    var fileData = new FormData();
    fileData.append("file", $("#file")[0].files[0]);
    fileData.append("requestId", $("#requestId").val());
    //console.log("FWOGGGGG");
    xhr.send(fileData);
} 

function downloadFile() 
{

    var xhr = new XMLHttpRequest();       

    xhr.open("GET","DropBoxServlet", true);

    var fileData2 = new FormData();
    fileData2.append("requestId2", $("#requestId2").val());
    console.log($("#requestId2").val());
    xhr.send(fileData2);
} 



window.onload = function()
{
	$("#uploadBtn").on("click", uploadFile);
	$("#downloadBtn").on("click", downloadFile);
}
