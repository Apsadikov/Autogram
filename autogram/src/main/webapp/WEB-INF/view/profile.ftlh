<#import "base.ftlh" as base>
<#import "user.ftlh" as user>
<@base.extends title="${title}" style="${style}" js="${js}">
    <@user.include id="${id}" name="${userProfile.name}" fullName="${userProfile.firstName} ${userProfile.lastName}"
    status="${userProfile.status}" img="${userProfile.avatar}"
    subscriber="${userProfile.subscriber}" subscription="${userProfile.subscription}" owner="${owner}"/>
    <#if owner == "true">
    <div class="row no-gutters add-post">
        <form action="" class="col-12">
            <textarea name="" id="" class="post-text" placeholder="Текст.."></textarea>
            <div id="gallery">
            </div>
            <div class="row no-gutters new-post-bottom">
                <div class="col-6">
                    <label for="file" class="label">
                        <i class="fa fa-camera fa-lg" aria-hidden="true"></i>
                        <input id="file" type="file" class="hidden" multiple>
                    </label>
                </div>
                <div class="col-6 send-col">
                    <div class="send">Отправить</div>
                </div>
            </div>
        </form>
    </div>
    </#if>
    <div class="row no-gutters gallery">
        <div class="col-4 image">
            <img src="https://sun9-2.userapi.com/c621729/v621729543/2ad70/h5vnVXja--U.jpg" alt="">
        </div>
        <div class="col-4 image">
            <img src="https://sun9-69.userapi.com/c621729/v621729543/2ad67/B9oHjRZ9-Ik.jpg" alt="">
        </div>
        <div class="col-4 image">
            <img src="https://sun9-11.userapi.com/c623930/v623930543/48b63/dOUtSBX8E80.jpg" alt="">
        </div>
    </div>
    <div class="row no-gutters gallery">
        <div class="col-4 image">
            <img src="https://sun9-15.userapi.com/c625721/v625721543/26f58/ZzNigKqzoM8.jpg" alt="">
        </div>
        <div class="col-4 image">
            <!--				<img src="https://sun9-69.userapi.com/c621729/v621729543/2ad67/B9oHjRZ9-Ik.jpg" alt="">-->
        </div>
        <div class="col-4 image">
            <!--				<img src="https://sun9-11.userapi.com/c623930/v623930543/48b63/dOUtSBX8E80.jpg" alt="">-->
        </div>
    </div>
</@base.extends>