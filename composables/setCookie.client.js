export default function setCookieClient (jwt){
document.cookie = `username:${jwt};path=/;expires=${new Date(new Date().getTime()+ (24*60*60*1000))}`
}