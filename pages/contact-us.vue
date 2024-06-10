<script setup lang="ts">
import shapeMove from "~/composables/shapeMove.client";

const breadcrumb = {
  title:"تماس با ما",
  breadcrumb: [
      {
        name: "تماس با ما",
        url: ''
      },
]
}
const data = {
  address: ' گرگان بلوار گلشهر خیابان پارتیا بین پارتیا  16 و 18',
  email: "esakhanzadi@gmail.com",
  phone: "09118738301",
  socialMedia: {
    instagram: "",
    telegram: "",
    linkedin: ""
  },
}
const form = reactive({
  name: "",
  email: "",
  subject: "",
  text: ""
})
onMounted(()=>{
  shapeMove()
})
</script>

<template>
  <div>
    <breadcrumb-header-title :data="breadcrumb"/>
    <section>
      <div class="container mx-auto ">
        <div class="form-box relative grid grid-cols-12 gap-5 mx-auto w-full px-7 md:px-0 md:w-3/4 lg:w-1/2 mb-10 lg:mb-0">
          <div class="content col-span-12 lg:col-span-5  w-full py-10 lg:py-20 ">
            <div class="content-title"><h4>ما همیشه مشتاق شنیدن از شما هستیم !</h4></div>
            <div class="contact-us-info flex flex-col gap-3 mt-5">
              <div class="address flex flex-col gap-2">
                <h5>آدرس </h5>
                <span>{{ data.address }}</span>
              </div>
              <div class="email flex flex-col gap-2">
                <h5>رایانامه</h5>
                <span>{{ data.email }}</span>
              </div>
              <div class="phone flex flex-col gap-2">
                <h5>تماس</h5>
                <span>{{ data.phone }}</span>
              </div>
            </div>
            <div class="mt-5">
              <social-app :data="data.socialMedia"/>
            </div>
          </div>
          <div class="form col-span-12 lg:col-span-7 lg:relative top-[15%] w-full p-10 lg:px-10 lg:pt-10 lg:pb-0 ">
            <div class="form-title flex flex-col gap-1.5 mb-3">
              <h5>در تماس باشید</h5>
              <p>برای رزرو جلسه مشاوره این فرم را پر کنید.</p>
            </div>
            <div>
              <form action="" @submit.prevent="handleSubmit" class="flex flex-col gap-5">
                <div>
                  <input type="text" class="py-1 px-2 border-b w-full outline-0" placeholder="* نام">
                </div>
                <div>
                  <input type="text" class="py-1 px-2 border-b w-full outline-0 " placeholder="* ایمیل شما">
                </div>
                <div>
                  <input type="text" class="py-1 px-2 border-b w-full outline-0" placeholder="* موضوع">
                </div>
                <div>
                  <textarea name="" id="" class="w-full outline-0 py-1 px-2 border-b h-20"
                            placeholder="* متن پیام"></textarea>
                </div>
                <div class="button">
                  <button type="submit"
                          class="flex justify-center items-center  gap-2 text-white py-1.5 px-3 w-fit rounded">
                    <i class="fa-solid fa-arrow-right-long"/>
                    <span>ارسال پیام</span>
                  </button>
                </div>
              </form>
            </div>
          </div>
                    <div class="shape hidden lg:block">
                      <div class="image-blue shape-move"><img src="~/assets/image/shape/blue-dot.png" alt="shape move"></div>
                      <div class="image-circle shape-move"><img src="~/assets/image/shape/circle-width-shadow.png" alt="shape move"></div>
                    </div>
        </div>
      </div>
    </section>
    <section>
      <div class="map w-full">
        <iframe class="w-full"
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3240.0755210476673!2d51.33512997634353!3d35.69975912895507!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3f8dfe05732c2e91%3A0xfcbec017befd15f4!2sAzadi%20Tower!5e0!3m2!1sen!2s!4v1717742149875!5m2!1sen!2s"
                width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"></iframe>
      </div>
    </section>
  </div>
</template>

<style scoped lang="scss">
@import "assets/style/_varible.scss";

.content-title {
  font-size: 1.2rem;
  font-weight: 600;
}

.contact-us-info {
  h5 {
    font-size: 0.8rem;
    font-weight: 600;
  }

  span {
    color: #808080;
    font-size: 0.8rem;
    line-height: 26px;
  }
}

.form {
  background: #FFFFFF;

  box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);

  .form-title {
    p {
      font-size: 0.8rem;
      color: #808080;
    }

    h5 {
      font-size: 1rem;
      font-weight: 700;
    }
  }
}

form {
  input, textarea {
    resize: block;
    transition: 0.3s;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);

    &::placeholder {
      font-size: 0.6rem;
    }

    &:focus {
      border-bottom: 1px solid #1ab69d;
    }
  }
}

button {
  font-size: 0.8rem;
  background: $linearGradiant-normal-button;
  transition: background 0.3s;

  &:hover {
    background: $linearGradient-button;
  }
}

.shape {
  .image-circle {
    position: absolute;
    scale: 0.7;
    left: 0;
    top: -5%;
  }
  .image-blue{
    position: absolute;
    scale: 0.7;
    left: -10%;
    top: 0;
    z-index: -1;
  }
}
</style>