var videoContainer; // object to hold video and associated info
var video;
var xhr = new XMLHttpRequest();
var playeventadded = false;
var commandReceived = false;
var loops = 0;
var width = $(window).width();   // returns width of browser viewport
var height = $(window).height();   // returns width of browser viewport
var canvas;
var context;
var img = new Image();
var title;
var interval;

$(document).ready(function(){
  canvas = document.getElementById('myCanvas');
  canvas.width = width;
  canvas.height = height;

  context = canvas.getContext('2d');

  title = document.getElementById('title');
  console.log("TITLE: " + title);
  poll();
  setInterval(play, 10);

  interval = setInterval(poll, 2000);
});

window.URL = window.URL || window.webkitURL;

xhr.onload = function(e) {
    var myBlob = this.response;

    console.log("xhr.onload", e, myBlob);
  if (this.status == 200) {
    var vid = window.URL.createObjectURL(myBlob);
    console.log('vid');
    console.log(vid);
    
    // not needed if autoplay is set for the video element
    
    var video = document.createElement("video");
    video.src = vid;
    video.loop = false;
    video.autoPlay = true;
    video.setAttribute('playsinline', 'playsinline');

    videoContainer = {
        video: video,
        ready: false,
    };

    document.body.appendChild(video);
    video.style.position= "absolute";
    video.style.top= "0";

    video.onended = function (e) {
      cmd = {};
      cmd.command = "videoended";
      cmd.sessionid = window.mosaic.sessionid;
      cmd.username = window.mosaic.username;
      $.ajax({
          type: 'POST',
          url: '/mosaic_query',
          data: JSON.stringify(cmd),
          dataType: 'json',
          success: function (dta, tStatus, req) {
              setInterval(replay, 3000);

          },
          error: function (req, tStatus, err) {
          }
      });
    }; 

    video.oncanplaythrough = readyToPlayVideo;
    videoContainer.video.muted=true;
    // videoContainer.video.play();
    document.getElementById("myCanvas").style.visibility="hidden";
    playeventadded = true;
    play();
  }
}

img.onload = function () {
    context.clearRect(0, 0, canvas.width, canvas.height);
    var hRatio = canvas.width / img.width;
    var vRatio = canvas.height / img.height;
    // var ratio = Math.min(hRatio, vRatio);
    var ratio = Math.max(hRatio, vRatio);

    var x = 0;
    var y = 0;
    var width = img.width;
    var height = img.height;
    var src = img.src;
    console.log('img onload', { hRatio, vRatio, ratio, width, height, src });

    context.drawImage(img, x, y, width, height, x, y, width * ratio, height * ratio);
    // context.drawImage(img, x, y, width, height);
};
// img.src = './images/woowoo.gif';

$(window).bind('beforeunload', function () {
    cmd = {};
    cmd.command = "leave";
    cmd.sessionid = window.mosaic.sessionid;
    cmd.username = window.mosaic.username;
    $.ajax({
        type: 'POST',
        url: '/mosaic_query',
        data: JSON.stringify(cmd),
        dataType: 'json',
        success: function (data, textStatus, request) {},
        error: function (request, textStatus, errorThrown) {}
    });
});

function doModal(etype,title,text) {
  BootstrapDialog.show({
    type: 'type-danger',
    title: title,
    message: text
  });
}

function play() {
    if(!commandReceived && playeventadded){     
   //TODO: wait for command from backend to play 
        cmd = {};
        cmd.command='videoloaded';
        cmd.sessionid = window.mosaic.sessionid;
        cmd.username = window.mosaic.username;
        $.ajax({
            type: 'POST',
            url: '/mosaic_query',
            data: JSON.stringify(cmd),
            dataType: 'json',
            success: playSuccess,
            error:function(data, req, errs){
                alert(errs);
            }
    });
  }
}

function playSuccess (data, textStatus, request) {
    console.log('Inside playSuccess', data, textStatus, request);
    commandReceived = true;
    var sleeptime = 0;
        if(data.play == 'ok'){
            if (typeof data.times[window.mosaic.sessionid] == "undefined"){
                sleeptime = 1000;
            }
            else{
                sleeptime = data.times[window.mosaic.sessionid];
            }
            //alert("time to sleep is "+ sleeptime);
            setTimeout(function(){
                playeventadded=false;
                document.getElementById("myCanvas").style.visibility="visible";
                videoContainer.video.currentTime = 0;
                videoContainer.video.muted=true;
                videoContainer.video.play();
            }, sleeptime);
            
    }
}

function replay() {
  //TODO: wait for command from backend to play 
  cmd = {};
  cmd.command='shouldplayagain';
  cmd.sessionid = window.mosaic.sessionid;
  cmd.username = window.mosaic.username;
  $.ajax({
    type: 'POST',
    url: '/mosaic_query',
    data: JSON.stringify(cmd),
    dataType: 'json',
    success: function (data, textStatus, request) {
      //alert(data.shouldplayagain);
      if(data.shouldplayagain == 'ok'){
        //alert("am here");
        videoContainer.video.currentTime = 0;
        //videoContainer.video.oncanplaythrough = readyToPlayVideo;
        videoContainer.video.play();
      }
    }
  });
}

function poll() {
    cmd = {};
    cmd.command = "query";
    cmd.loop = loops;
    cmd.sessionid = window.mosaic.sessionid;
    cmd.username = window.mosaic.username;
    $.ajax({
        type: 'POST',
        url: '/mosaic_query',
        data: JSON.stringify(cmd),
        dataType: 'json',
        success: pollSuccess,
        error: function (request, textStatus, errorThrown) {
            clearInterval(interval);
            var image = document.getElementById('image');
            if(!image)
                console.log('Image is empty', image);
            else
            image.src =
                    "http://us.cdn4.123rf.com/168nwm/dvarg/dvarg1209/dvarg120900003/15019446-skull-and-crossbones--a-mark-of-the-danger-warning.jpg";
            doModal("Error", "Network error, system is halted");
        }
    });
}

function pollSuccess(data, textStatus, request) {
    if (request.status == 204) {
        text = "";
    } else {
        text = request.responseText;
        data = JSON.parse(text);
        text = JSON.stringify(data, null, 2);
        if (data.image != null) {
            if (data.image.payload.endsWith("mp4") ||data.image.payload.endsWith("mov")||data.image.payload.endsWith("avi")||data.image.payload.endsWith("wmv")) {

                xhr.open('GET', data.image.payload, true);
                xhr.responseType = 'blob';
                console.log('about to send XHR request', xhr);
                xhr.send();
            } else {
                var payload = data.image.payload;
                img.src = payload;
            }
            loops++;
            var title = document.getElementById('title').innerText;
            var sequence = data.sequence;
            document.getElementById('title').value = title;
            if (title === "Skramblee") {
                document.getElementById('title').innerText = "Skramblee: " + sequence;
            }
        }
    }
}

function readyToPlayVideo(event) {
    // the video may not match the canvas size so find a scale to fit
    // the video can be played so hand it off to the display function
    videoContainer.scale = Math.min(
            canvas.width / this.videoWidth,
            canvas.height / this.videoHeight);

    videoContainer.ready = true;
    
    requestAnimationFrame(updateCanvas);
}

function updateCanvas() {
    context.clearRect(0, 0, canvas.width, canvas.height);
    // only draw if loaded and ready
    if (videoContainer !== undefined && videoContainer.ready) {
        // find the top left of the video on the canvas
        var scale = videoContainer.scale;
        var vidH = videoContainer.video.videoHeight;
        var vidW = videoContainer.video.videoWidth;
        var top = canvas.height / 2 - (vidH / 2) * scale;
        var left = canvas.width / 2 - (vidW / 2) * scale;

        // console.log('updateCanvas', {scale, vidH, vidW, top, left} );

        // now just draw the video the correct size
        context.drawImage(videoContainer.video, 0,0, vidW, vidH,0,0,vidW*videoContainer.scale, vidH*videoContainer.scale);
    }
    // all done for display 
    // request the next frame in 1/60th of a second
    requestAnimationFrame(updateCanvas);
}
