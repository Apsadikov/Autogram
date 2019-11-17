<#import "base.ftlh" as base>
<@base.extends title="${title}" style="${style}" js="${js}">
		<div class="row profile no-gutters">
			<div class="col-2">
				<img src="https://sun9-61.userapi.com/c624418/v624418543/4fca9/ZlGtFq6vi_4.jpg?ava=1" class="avatar" alt="">
			</div>
			<div class="col-8 user-info">
				<div class="user-info-wrapper">
					<div>
						<span class="nickname">Ivan_018</span>
						<span class="fullname">(Ivan Ivanov)</span>
					</div>
					<div class="status">
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel justo nunc. Donec tellus sem, tincidunt nec accumsan quis, tempus eu elit.
					</div>
					<div class="social-actions">
						<div class="subscribe">Подписаться</div>
						<div class="message">Сообщение</div>
					</div>
				</div>
				<div class="row no-gutters subscriber-info">
					<div class="col-6">
						<div class="subscriber-info-name">Подписчики</div>
						<a href="" class="number">648</a>
					</div>
					<div class="col-6">
						<div class="subscriber-info-name">Подписки</div>
						<a href="" class="number">243</a>
					</div>
				</div>
			</div>
		</div>
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
	</div>
</@base.extends>