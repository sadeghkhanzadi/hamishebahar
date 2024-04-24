<script setup>
const error = useError()
if (error.value.statusCode === 404 || "404"){
  error.value.message = "صفحه مورد نظر یافت نشد ."
}else if(error.value.statusCode === 500 || "500"){
  error.value.message = "خطای سرور لطلفا مجدد امتحان کنید ."
}
function redirect(){
  clearError({redirect:"/"})
}
</script>

<template>
  <div class="container mx-auto">
    <div class="row">
      <div class="error flex justify-center items-center h-[100] flex-col mt-[10vh] p-5">
        <div class="image-error">
          <img src="@/assets/image/error/404.svg" alt="error image">
        </div>
        <div class="error-content">
          <h1 class="text-center px-5  w-1/2 mx-auto flex flex-row-reverse justify-center gap-1 items-center text-white  rounded-lg pt-2 shadow-sm">
            <span>{{ error.statusCode }}</span>
            <span>ERROR</span>
          </h1>
          <p class="text-center mt-4 ">{{  error.message.split(":")[0] }}</p>
        <div class="redirect  ">
          <button class="btn bg-sky-00 shadow-sm mt-5 w-full py-2 rounded-lg  font-bold transition duration-300 ease-linear hover:text-white" @click="redirect">برگشت به صفحه اصلی</button>
        </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
h1 {
  background-color: rgb(52, 152, 219);
}

h1 span:last-child{
  font-weight: lighter;
  font-size: 20px;
}

h1 span:first-child{
  font-weight: bolder;
  font-size: 24px;
}

p {
  color: rgb(52, 152, 219);
  font-size:30px;
}
@media screen and (max-width: 768px) {
  img{
    width: 300px;
  }
  p{
    font-size: 20px;
  }
}
.btn{
  border: 2px solid #3498DBFF;
  color: #3498DBFF;
}
.btn:hover{
  color: white;
  background: #3498DBFF;
}
</style>