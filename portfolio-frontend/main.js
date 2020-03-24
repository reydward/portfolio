const queryString = window.location.search;

function getTimeline(){
  var timelineDiv=document.getElementById("timeline");
  const urlParams = new URLSearchParams(queryString);
  const user = urlParams.getAll('user');
  const count = urlParams.getAll('count');
  const endpoint = 'http://localhost:8888/portfolio/v1/timeline?user='+user+'&count='+count;
  let headers = new Headers();
  headers.append('Accept', 'application/json');
  let encoded = window.btoa('username:password');
  let auth = 'Basic ' + encoded;
  headers.append('Authorization', auth);

  let request = new Request(endpoint, {
    method: 'GET',
    headers: headers,
    credentials: 'same-origin'
  });

  fetch(request)
  .then((response)=>{
    if(response.ok){
      return response.json();
    } else {
      throw new Error('BAD HTTP stuff');
    }
  })
  .then((post)=>{
    post.timeline.forEach(function(tweet) {
      timelineDiv.innerHTML+=`
      <div>
        <p><b>"${tweet.text}"</b></p>
      </div>`
    });

  })
  .catch((error)=>{
    console.log(error);
  })
}

function getPortfolio(){
  var photoDiv=document.getElementById("profile-photo");
  var userTimeline=document.getElementById("user-timeline");
  var profileName=document.getElementById("profile-name");
  var profileDescription=document.getElementById("profile-description");

  const urlParams = new URLSearchParams(queryString);
  const user = urlParams.getAll('user');
  const endpoint = 'http://localhost:8888/portfolio/v1/information/'+user;
  console.log(endpoint);

  fetch(endpoint)
  .then((response)=>{
    return response.json();
  })
  .then((post)=>{
    photoDiv.innerHTML+=`<img src="${post.imageUrl}"/>`
    userTimeline.innerHTML+=`${post.twitterUserName}'s Timeline`
    profileName.innerHTML+=`${post.title}'s Timeline`
    profileDescription.innerHTML+=`${post.description}`
  })
  .catch((error)=>{
    console.log(error);
  })
}