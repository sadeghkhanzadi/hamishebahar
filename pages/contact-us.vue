<script setup lang="ts">
const data = {
  address: 'گرگان بلوار گلشهر خیابان پارتیا بین پارتیا  16 و 18',
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
</script>

<template>
  <div>
    <breadcrumb-header-title/>
    <section>
      <div class="container mx-auto">
        <div class="form-box  grid grid-cols-12 gap-5 w-2/3 mx-auto">
          <div class="content col-span-5 w-full">
            <div class="content-title"><h4>ما همیشه مشتاق شنیدن از شما هستیم !</h4></div>
            <div class="contact-us-info">
              <div class="address">
                <h5>آدرس </h5>
                <span>{{ data.address }}</span>
              </div>
              <div class="email">
                <h5>رایانامه</h5>
                <span>{{ data.email }}</span>
              </div>
              <div class="phone">
                <h5>تماس</h5>
                <span>{{ data.phone }}</span>
              </div>
            </div>
            <div class="social-media">
              <ul class="flex gap-3 items-center justify-center ">
                <li class="telegram px-2 py-4 rounded-full">
                  <nuxtLink class="flex justify-center items-center" :to="data.socialMedia.telegram"><i
                      class="fa-brands fa-telegram-plane fa-lg"/></nuxtLink>
                </li>
                <li class="instagram px-2 py-4 rounded-full">
                  <nuxtLink class="flex justify-center items-center" :to="data.socialMedia.instagram"><i
                      class="fa-brands fa-instagram fa-lg"/></nuxtLink>
                </li>
                <li class="linkedin px-2 py-4 rounded-full">
                  <nuxtLink class="flex justify-center items-center" :to="data.socialMedia.linkedin"><i
                      class="fa-brands fa-linkedin-in fa-lg"/></nuxtLink>
                </li>
              </ul>
            </div>
          </div>
          <div class="form col-span-7 p-10 shadow">
            <div class="form-title">
              <h5>در تماس باشید</h5>
              <p>برای رزرو جلسه مشاوره این فرم را پر کنید.</p>
            </div>
            <div class="form">
              <form action="" @submit.prevent="handleSubmit" class="flex flex-col gap-5">
                <div>
                  <input type="text" class="py-1 px-2 border-b w-full outline-0" placeholder=" نام">
                </div>
                <div>
                  <input type="text" class="py-1 px-2 border-b w-full outline-0" placeholder="ایمیل شما">
                </div>
                <div>
                  <input type="text" class="py-1 px-2 border-b w-full outline-0" placeholder="موضوع">
                </div>
                <div>
                  <textarea name="" id="" class="w-full outline-0 py-1 px-2 border-b">متن پیام</textarea>
                </div>
                <div class="button">
                  <button type="submit" class="flex justify-center items-center">
                    <span class="icon"> <i class="fa-solid fa-arrow-right-long"/></span>
                    <span>ارسال پیام</span>
                  </button>
                </div>
              </form>
            </div>
          </div>
          <div class="shape">
            <div class="image"><img src="~/assets/image/shape/blue-dot.png" alt="shape move"></div>
            <div class="image"><img src="~/assets/image/shape/circle-width-shadow.png" alt="shape move"></div>
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
.social-media {
  color: rgb(167, 167, 167);

  li {
    transition: 0.3s;
    border: 1px solid rgba(0, 0, 0, 0.1);

    &:hover {
      background: #1ab69d;
      color: #FFFFFF;
    }
  }
}

form {
  input, textarea {
    transition: 0.3s;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);

    &:focus {
      border-bottom: 1px solid #1ab69d;
    }
  }
}

.shape {
  img {
    scale: 0.8;
  }
}
</style>