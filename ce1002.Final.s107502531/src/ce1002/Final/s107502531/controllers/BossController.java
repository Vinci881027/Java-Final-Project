package ce1002.Final.s107502531.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import ce1002.Final.s107502531.Final;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class BossController implements Initializable {
	@FXML
	private AnchorPane _field;
	@FXML
	private Rectangle _bloodRec1P, _flashRec1P, _bloodRec2P, _flashRec2P, _bossBlood1, _bossBlood2;
	private int _bloodNum1P = 100, _flashNum1P = 100, _bloodNum2P = 100, _flashNum2P = 100, _bossBloodNum1 = 100,
			_bossBloodNum2 = 100;
	@FXML
	private ImageView _scene, _role1, _role2, _role3, _role4, _role5, _boss;
	@FXML
	private ImageView _weapon1, _weapon2, _weapon3, _weapon4, _weapon5, _laser;
	private ImageView _role1P = new ImageView();
	private ImageView _role2P = new ImageView();
	private ImageView _weapon1P = new ImageView();
	private ImageView _weapon2P = new ImageView();
	private boolean gameOver = false;
	private boolean death1P = false, death2P = false;
	private int bossMove = 1;
	private boolean w = false, a = false, d = false, j = false, up = false, left = false, right = false, num1 = false;
	private int jumpCount1P = 0, downCount1P = 0, jumpCount2P = 0, downCount2P = 0;
	private int shootCount1P = 0, shootCount2P = 0;
	private int teleportCount1P = 0, teleportCount2P = 0;
	public static int bossWinner = 0;
	LinkedList<ImageView> _bulletsA = new LinkedList<ImageView>();
	LinkedList<ImageView> _bulletsD = new LinkedList<ImageView>();
	LinkedList<ImageView> _bulletsLeft = new LinkedList<ImageView>();
	LinkedList<ImageView> _bulletsRight = new LinkedList<ImageView>();
	LinkedList<ImageView> _lasers = new LinkedList<ImageView>();
	private MediaPlayer shoot1P;
	private MediaPlayer shoot2P;
	private MediaPlayer flash1P;
	private MediaPlayer flash2P;
	private MediaPlayer hurt1P;
	private MediaPlayer hurt2P;
	private MediaPlayer hurtBoss;
	private MediaPlayer bossLaser;
	private Timeline fpsWeaponMove = new Timeline();
	private Timeline fpsLaser = new Timeline();

	public void gameOver() throws IOException {
		fpsWeaponMove.stop();
		fpsLaser.stop();
		FXMLLoader loadder = new FXMLLoader(getClass().getResource("../views/GameOverWindow.fxml"));
		Parent GameOver = loadder.load();
		Scene GameOverScene = new Scene(GameOver);
		Final.mainStage.setScene(GameOverScene);
		Final.mainStage.setFullScreen(true);
	}

	public EventHandler<KeyEvent> OnKeyPressed = (e) -> { // keyboard press
		if (e.getCode() == KeyCode.W) {
			if (death1P == false)
				w = true;
		}
		if (e.getCode() == KeyCode.A) {
			if (death1P == false)
				a = true;
		}
		if (e.getCode() == KeyCode.D) {
			if (death1P == false)
				d = true;
		}
		if (e.getCode() == KeyCode.UP) {
			if (death2P == false)
				up = true;
		}
		if (e.getCode() == KeyCode.LEFT) {
			if (death2P == false)
				left = true;
		}
		if (e.getCode() == KeyCode.RIGHT) {
			if (death2P == false)
				right = true;
		}
		if (e.getCode() == KeyCode.J) {
			if (death1P == false) {
				if (j == false) {
					Media media = new Media(getClass().getResource("../resource/shoot.mp3").toString());
					shoot1P = new MediaPlayer(media);
					shoot1P.play();
					j = true;
				}
			}
		}
		if (e.getCode() == KeyCode.NUMPAD1) {
			if (death2P == false) {
				if (num1 == false) {
					Media media = new Media(getClass().getResource("../resource/shoot.mp3").toString());
					shoot2P = new MediaPlayer(media);
					shoot2P.play();
					num1 = true;
				}
			}
		}
		if (e.getCode() == KeyCode.K) {
			if (death1P == false) {
				if (_flashNum1P == 100) {
					Media media = new Media(getClass().getResource("../resource/flash.mp3").toString());
					flash1P = new MediaPlayer(media);
					flash1P.play();
					Timeline teleport1P = new Timeline(new KeyFrame(Duration.millis(10), (event) -> {
						_role1P.setOpacity(_role1P.getOpacity() - 0.1);
						teleportCount1P++;
						if (teleportCount1P == 10) {
							if (_role1P.getRotate() == 180) {
								_role1P.setLayoutX(_role1P.getLayoutX() - 300);
								if (_role1P.getLayoutX() < 0)
									_role1P.setLayoutX(0);
							} else {
								_role1P.setLayoutX(_role1P.getLayoutX() + 300);
								if (_role1P.getLayoutX() + _role1P.getFitWidth() > _scene.getFitWidth())
									_role1P.setLayoutX(_scene.getFitWidth() - _role1P.getFitWidth());
							}
							_role1P.setOpacity(1.0);
							_flashNum1P = 0;
							_flashRec1P.setWidth(0);
							teleportCount1P = 0;
						}
					}));
					teleport1P.setCycleCount(10);
					teleport1P.play();
				}
			}
		}
		if (e.getCode() == KeyCode.NUMPAD2) {
			if (death2P == false) {
				if (_flashNum2P == 100) {
					Media media = new Media(getClass().getResource("../resource/flash.mp3").toString());
					flash2P = new MediaPlayer(media);
					flash2P.play();
					Timeline teleport2P = new Timeline(new KeyFrame(Duration.millis(10), (event) -> {
						_role2P.setOpacity(_role2P.getOpacity() - 0.1);
						teleportCount2P++;
						if (teleportCount2P == 10) {
							if (_role2P.getRotate() == 180) {
								_role2P.setLayoutX(_role2P.getLayoutX() - 300);
								if (_role2P.getLayoutX() < 0)
									_role2P.setLayoutX(0);
							} else {
								_role2P.setLayoutX(_role2P.getLayoutX() + 300);
								if (_role2P.getLayoutX() + _role2P.getFitWidth() > _scene.getFitWidth())
									_role2P.setLayoutX(_scene.getFitWidth() - _role2P.getFitWidth());
							}
							_role2P.setOpacity(1.0);
							_flashNum2P = 0;
							_flashRec2P.setWidth(0);
							teleportCount2P = 0;
						}
					}));
					teleport2P.setCycleCount(10);
					teleport2P.play();
				}
			}
		}
	};
	public EventHandler<KeyEvent> OnKeyReleased = (e) -> { // keyboard release
		if (e.getCode() == KeyCode.A)
			a = false;
		if (e.getCode() == KeyCode.D)
			d = false;
		if (e.getCode() == KeyCode.LEFT)
			left = false;
		if (e.getCode() == KeyCode.RIGHT)
			right = false;
	};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { // initialize
		if (RoleController._1PVisible == 0) { // initialize 1P
			_role1P.setImage(_role1.getImage());
			_role1P.setFitHeight(_role1.getFitHeight());
			_role1P.setFitWidth(_role1.getFitWidth());
			_role1P.setLayoutX(30);
			_role1P.setLayoutY(_role1.getLayoutY());
			_weapon1P.setImage(_weapon1.getImage());
			_weapon1P.setFitHeight(_weapon1.getFitHeight());
			_weapon1P.setFitWidth(_weapon1.getFitWidth());
		} else if (RoleController._1PVisible == 1) {
			_role1P.setImage(_role2.getImage());
			_role1P.setFitHeight(_role2.getFitHeight());
			_role1P.setFitWidth(_role2.getFitWidth());
			_role1P.setLayoutX(30);
			_role1P.setLayoutY(_role2.getLayoutY());
			_weapon1P.setImage(_weapon2.getImage());
			_weapon1P.setFitHeight(_weapon2.getFitHeight());
			_weapon1P.setFitWidth(_weapon2.getFitWidth());
		} else if (RoleController._1PVisible == 2) {
			_role1P.setImage(_role3.getImage());
			_role1P.setFitHeight(_role3.getFitHeight());
			_role1P.setFitWidth(_role3.getFitWidth());
			_role1P.setLayoutX(30);
			_role1P.setLayoutY(_role3.getLayoutY());
			_weapon1P.setImage(_weapon3.getImage());
			_weapon1P.setFitHeight(_weapon3.getFitHeight());
			_weapon1P.setFitWidth(_weapon3.getFitWidth());
		} else if (RoleController._1PVisible == 3) {
			_role1P.setImage(_role4.getImage());
			_role1P.setFitHeight(_role4.getFitHeight());
			_role1P.setFitWidth(_role4.getFitWidth());
			_role1P.setLayoutX(30);
			_role1P.setLayoutY(_role4.getLayoutY());
			_weapon1P.setImage(_weapon4.getImage());
			_weapon1P.setFitHeight(_weapon4.getFitHeight());
			_weapon1P.setFitWidth(_weapon4.getFitWidth());
		} else if (RoleController._1PVisible == 4) {
			_role1P.setImage(_role5.getImage());
			_role1P.setFitHeight(_role5.getFitHeight());
			_role1P.setFitWidth(_role5.getFitWidth());
			_role1P.setLayoutX(30);
			_role1P.setLayoutY(_role5.getLayoutY());
			_weapon1P.setImage(_weapon5.getImage());
			_weapon1P.setFitHeight(_weapon5.getFitHeight());
			_weapon1P.setFitWidth(_weapon5.getFitWidth());
		}
		_role1P.setVisible(true);
		_role1P.setPreserveRatio(true);
		_role1P.setRotationAxis(Rotate.Y_AXIS);
		_field.getChildren().add(_role1P);
		_weapon1P.setVisible(false);
		_weapon1P.setPreserveRatio(true);
		_weapon1P.setRotationAxis(Rotate.Y_AXIS);
		_field.getChildren().add(_weapon1P);
		if (RoleController._2PVisible == 0) { // initialize 2P
			_role2P.setImage(_role1.getImage());
			_role2P.setFitHeight(_role1.getFitHeight());
			_role2P.setFitWidth(_role1.getFitWidth());
			_role2P.setLayoutX(_scene.getFitWidth() - 116);
			_role2P.setLayoutY(_role1.getLayoutY());
			_weapon2P.setImage(_weapon1.getImage());
			_weapon2P.setFitHeight(_weapon1.getFitHeight());
			_weapon2P.setFitWidth(_weapon1.getFitWidth());
		} else if (RoleController._2PVisible == 1) {
			_role2P.setImage(_role2.getImage());
			_role2P.setFitHeight(_role2.getFitHeight());
			_role2P.setFitWidth(_role2.getFitWidth());
			_role2P.setLayoutX(_scene.getFitWidth() - 116);
			_role2P.setLayoutY(_role2.getLayoutY());
			_weapon2P.setImage(_weapon2.getImage());
			_weapon2P.setFitHeight(_weapon2.getFitHeight());
			_weapon2P.setFitWidth(_weapon2.getFitWidth());
		} else if (RoleController._2PVisible == 2) {
			_role2P.setImage(_role3.getImage());
			_role2P.setFitHeight(_role3.getFitHeight());
			_role2P.setFitWidth(_role3.getFitWidth());
			_role2P.setLayoutX(_scene.getFitWidth() - 116);
			_role2P.setLayoutY(_role3.getLayoutY());
			_weapon2P.setImage(_weapon3.getImage());
			_weapon2P.setFitHeight(_weapon3.getFitHeight());
			_weapon2P.setFitWidth(_weapon3.getFitWidth());
		} else if (RoleController._2PVisible == 3) {
			_role2P.setImage(_role4.getImage());
			_role2P.setFitHeight(_role4.getFitHeight());
			_role2P.setFitWidth(_role4.getFitWidth());
			_role2P.setLayoutX(_scene.getFitWidth() - 116);
			_role2P.setLayoutY(_role4.getLayoutY());
			_weapon2P.setImage(_weapon4.getImage());
			_weapon2P.setFitHeight(_weapon4.getFitHeight());
			_weapon2P.setFitWidth(_weapon4.getFitWidth());
		} else if (RoleController._2PVisible == 4) {
			_role2P.setImage(_role5.getImage());
			_role2P.setFitHeight(_role5.getFitHeight());
			_role2P.setFitWidth(_role5.getFitWidth());
			_role2P.setLayoutX(_scene.getFitWidth() - 116);
			_role2P.setLayoutY(_role5.getLayoutY());
			_weapon2P.setImage(_weapon5.getImage());
			_weapon2P.setFitHeight(_weapon5.getFitHeight());
			_weapon2P.setFitWidth(_weapon5.getFitWidth());
		}
		_role2P.setVisible(true);
		_role2P.setPreserveRatio(true);
		_role2P.setRotationAxis(Rotate.Y_AXIS);
		_role2P.setRotate(180);
		_field.getChildren().add(_role2P);
		_weapon2P.setVisible(false);
		_weapon2P.setPreserveRatio(true);
		_weapon2P.setRotationAxis(Rotate.Y_AXIS);
		_weapon2P.setRotate(180);
		_field.getChildren().add(_weapon2P);
		_field.getChildren().remove(_role1); // remove original ImageView
		_field.getChildren().remove(_role2);
		_field.getChildren().remove(_role3);
		_field.getChildren().remove(_role4);
		_field.getChildren().remove(_role5);
		_field.getChildren().remove(_weapon1);
		_field.getChildren().remove(_weapon2);
		_field.getChildren().remove(_weapon3);
		_field.getChildren().remove(_weapon4);
		_field.getChildren().remove(_weapon5);
		KeyFrame KWeaponMove = new KeyFrame(Duration.millis(1), (e) -> { // let weapon move
			ArrayList<ImageView> tLasers = new ArrayList<ImageView>(_lasers);
			for (var b : tLasers) {
				if (_bossBloodNum1 > 0) {
					b.setLayoutX(b.getLayoutX() + Math.cos(b.getRotate() * Math.PI / 180) * 2);
					b.setLayoutY(b.getLayoutY() + Math.sin(b.getRotate() * Math.PI / 180) * 2);
				} else {
					b.setLayoutX(b.getLayoutX() + Math.cos(b.getRotate() * Math.PI / 180) * 4);
					b.setLayoutY(b.getLayoutY() + Math.sin(b.getRotate() * Math.PI / 180) * 4);
				}
				if (_bloodNum1P > 0) {
					if (b.getLayoutX() + b.getFitWidth() / 2 > _role1P.getLayoutX() + _role1P.getFitWidth() / 2 - 1
							&& b.getLayoutX() + b.getFitWidth() / 2 < _role1P.getLayoutX() + _role1P.getFitWidth() / 2
									+ 1
							&& b.getLayoutY() + b.getFitHeight() > _role1P.getLayoutY()
							&& b.getLayoutY() < _role1P.getLayoutY() + _role1P.getFitHeight()) {
						_bloodNum1P = _bloodNum1P - 6;
						_bloodRec1P.setWidth(267 * _bloodNum1P / 100);
						Media media = new Media(getClass().getResource("../resource/hurt.mp3").toString());
						hurt1P = new MediaPlayer(media);
						hurt1P.play();
					}
				} else {
					_field.getChildren().remove(_role1P);
					_field.getChildren().remove(_flashRec1P);
					death1P = true;
				}
				if (_bloodNum2P > 0) {
					if (b.getLayoutX() + b.getFitWidth() / 2 > _role2P.getLayoutX() + _role2P.getFitWidth() / 2 - 1
							&& b.getLayoutX() + b.getFitWidth() / 2 < _role2P.getLayoutX() + _role2P.getFitWidth() / 2
									+ 1
							&& b.getLayoutY() + b.getFitHeight() > _role2P.getLayoutY()
							&& b.getLayoutY() < _role2P.getLayoutY() + _role2P.getFitHeight()) {
						_bloodNum2P = _bloodNum2P - 6;
						_bloodRec2P.setWidth(267 * _bloodNum2P / 100);
						Media media = new Media(getClass().getResource("../resource/hurt.mp3").toString());
						hurt2P = new MediaPlayer(media);
						hurt2P.play();
					}
				} else {
					_role2P.setVisible(false);
					_flashRec2P.setVisible(false);
					death2P = true;
				}
				if (_bloodNum1P <= 0 && _bloodNum2P <= 0) {
					try {
						if (gameOver == false) {
							bossWinner = 4;
							gameOver();
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (b.getLayoutX() <= 140 || b.getLayoutX() >= _scene.getFitWidth() - b.getFitWidth()
						|| b.getLayoutY() <= 0 || b.getLayoutY() >= _scene.getFitHeight() - b.getFitHeight()) {
					_lasers.remove(b);
					_field.getChildren().remove(b);
				}
			}
			ArrayList<ImageView> tBulletsA = new ArrayList<ImageView>(_bulletsA);
			for (var b : tBulletsA) {
				b.setLayoutX(b.getLayoutX() - 1);
				if (b.getLayoutX() > _boss.getLayoutX() - 1 && b.getLayoutX() < _boss.getLayoutX() + 1
						&& b.getLayoutY() + b.getFitHeight() > _boss.getLayoutY()
						&& b.getLayoutY() < _boss.getLayoutY() + _boss.getFitHeight()) {
					if (_bossBloodNum1 > 0) {
						_bossBloodNum1 = _bossBloodNum1 - 2;
						_bossBlood1.setWidth(267 * _bossBloodNum1 / 100);
					} else {
						_bossBloodNum2 = _bossBloodNum2 - 2;
						_bossBlood2.setWidth(267 * _bossBloodNum2 / 100);
					}
					Media media = new Media(getClass().getResource("../resource/boss_hurt.mp3").toString());
					hurtBoss = new MediaPlayer(media);
					hurtBoss.play();
					if (_bossBloodNum2 == 0) {
						try {
							if (gameOver == false) {
								if (_bloodNum1P > 0 && _bloodNum2P > 0)
									bossWinner = 3;
								else
									bossWinner = 1;
								gameOver();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (b.getLayoutX() - _role1P.getLayoutX() < -300 || b.getLayoutX() <= 0) {
					_bulletsA.remove(b);
					_field.getChildren().remove(b);
				}
			}
			ArrayList<ImageView> tBulletsD = new ArrayList<ImageView>(_bulletsD);
			for (var b : tBulletsD) {
				b.setLayoutX(b.getLayoutX() + 1);
				if (b.getLayoutX() > _boss.getLayoutX() - 1 && b.getLayoutX() < _boss.getLayoutX() + 1
						&& b.getLayoutY() + b.getFitHeight() > _boss.getLayoutY()
						&& b.getLayoutY() < _boss.getLayoutY() + _boss.getFitHeight()) {
					if (_bossBloodNum1 > 0) {
						_bossBloodNum1 = _bossBloodNum1 - 2;
						_bossBlood1.setWidth(267 * _bossBloodNum1 / 100);
					} else {
						_bossBloodNum2 = _bossBloodNum2 - 2;
						_bossBlood2.setWidth(267 * _bossBloodNum2 / 100);
					}
					Media media = new Media(getClass().getResource("../resource/boss_hurt.mp3").toString());
					hurtBoss = new MediaPlayer(media);
					hurtBoss.play();
					if (_bossBloodNum2 == 0) {
						try {
							if (gameOver == false) {
								if (_bloodNum1P > 0 && _bloodNum2P > 0)
									bossWinner = 3;
								else
									bossWinner = 1;
								gameOver();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (b.getLayoutX() - _role1P.getLayoutX() > 300
						|| b.getLayoutX() >= _scene.getFitWidth() - b.getFitWidth()) {
					_bulletsD.remove(b);
					_field.getChildren().remove(b);
				}
			}
			ArrayList<ImageView> tBulletsLeft = new ArrayList<ImageView>(_bulletsLeft);
			for (var b : tBulletsLeft) {
				b.setLayoutX(b.getLayoutX() - 1);
				if (b.getLayoutX() > _boss.getLayoutX() - 1 && b.getLayoutX() < _boss.getLayoutX() + 1
						&& b.getLayoutY() + b.getFitHeight() > _boss.getLayoutY()
						&& b.getLayoutY() < _boss.getLayoutY() + _boss.getFitHeight()) {
					if (_bossBloodNum1 > 0) {
						_bossBloodNum1 = _bossBloodNum1 - 2;
						_bossBlood1.setWidth(267 * _bossBloodNum1 / 100);
					} else {
						_bossBloodNum2 = _bossBloodNum2 - 2;
						_bossBlood2.setWidth(267 * _bossBloodNum2 / 100);
					}
					Media media = new Media(getClass().getResource("../resource/boss_hurt.mp3").toString());
					hurtBoss = new MediaPlayer(media);
					hurtBoss.play();
					if (_bossBloodNum2 == 0) {
						try {
							if (gameOver == false) {
								if (_bloodNum1P > 0 && _bloodNum2P > 0)
									bossWinner = 3;
								else
									bossWinner = 2;
								gameOver();
							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (b.getLayoutX() - _role2P.getLayoutX() < -300 || b.getLayoutX() <= 0) {
					_bulletsLeft.remove(b);
					_field.getChildren().remove(b);
				}
			}
			ArrayList<ImageView> tBulletsRight = new ArrayList<ImageView>(_bulletsRight);
			for (var b : tBulletsRight) {
				b.setLayoutX(b.getLayoutX() + 1);
				if (b.getLayoutX() > _boss.getLayoutX() - 1 && b.getLayoutX() < _boss.getLayoutX() + 1
						&& b.getLayoutY() + b.getFitHeight() > _boss.getLayoutY()
						&& b.getLayoutY() < _boss.getLayoutY() + _boss.getFitHeight()) {
					if (_bossBloodNum1 > 0) {
						_bossBloodNum1 = _bossBloodNum1 - 2;
						_bossBlood1.setWidth(267 * _bossBloodNum1 / 100);
					} else {
						_bossBloodNum2 = _bossBloodNum2 - 2;
						_bossBlood2.setWidth(267 * _bossBloodNum2 / 100);
					}
					Media media = new Media(getClass().getResource("../resource/boss_hurt.mp3").toString());
					hurtBoss = new MediaPlayer(media);
					hurtBoss.play();
					if (_bossBloodNum2 == 0) {
						try {
							if (_bloodNum1P > 0 && _bloodNum2P > 0)
								bossWinner = 3;
							else
								bossWinner = 2;
							gameOver();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				if (b.getLayoutX() - _role2P.getLayoutX() > 300
						|| b.getLayoutX() >= _scene.getFitWidth() - b.getFitWidth()) {
					_bulletsRight.remove(b);
					_field.getChildren().remove(b);
				}
			}
		});
		fpsWeaponMove.getKeyFrames().addAll(KWeaponMove);
		fpsWeaponMove.setCycleCount(Timeline.INDEFINITE);
		fpsWeaponMove.play();
		Timeline fpsFlash = new Timeline(new KeyFrame(Duration.millis(50), (event) -> { // flash
			if (_flashNum1P < 100) {
				_flashNum1P++;
				_flashRec1P.setWidth(267 * _flashNum1P / 100);
			}
			if (_flashNum2P < 100) {
				_flashNum2P++;
				_flashRec2P.setWidth(267 * _flashNum2P / 100);
			}
		}));
		fpsFlash.setCycleCount(Timeline.INDEFINITE);
		fpsFlash.play();
		KeyFrame KLaser = new KeyFrame(Duration.millis(1500), (e) -> { // generate a laser
			ImageView newLaser = new ImageView();
			newLaser.setImage(_laser.getImage());
			newLaser.setFitWidth(_laser.getFitWidth());
			newLaser.setFitHeight(_laser.getFitHeight());
			newLaser.setLayoutX(_boss.getLayoutX() - _boss.getFitWidth() / 2);
			newLaser.setLayoutY(_boss.getLayoutY() + _boss.getFitHeight() / 2 - _laser.getFitHeight() / 2);
			newLaser.setPreserveRatio(true);
			if ((Math
					.pow((_role1P.getLayoutX() + _role1P.getFitWidth() / 2)
							- (_boss.getLayoutX() + _boss.getFitWidth() / 2), 2)
					+ Math.pow(
							(_role1P.getLayoutY() + _role1P.getFitHeight() / 2)
									- (_boss.getLayoutY() + _boss.getFitHeight() / 2),
							2) < Math
									.pow((_role2P.getLayoutX() + _role2P.getFitWidth() / 2)
											- (_boss.getLayoutX() + _boss.getFitWidth() / 2), 2)
									+ Math.pow((_role2P.getLayoutY() + _role2P.getFitHeight() / 2)
											- (_boss.getLayoutY() + _boss.getFitHeight() / 2), 2)
					&& _bloodNum1P > 0) || _bloodNum2P <= 0) {
				newLaser.setRotate(Math.atan2(
						(_role1P.getLayoutY() + _role1P.getFitHeight() / 2)
								- (newLaser.getLayoutY() + newLaser.getFitHeight() / 2),
						(_role1P.getLayoutX() + _role1P.getFitWidth() / 2)
								- (newLaser.getLayoutX() + newLaser.getFitWidth() / 2))
						* 180 / Math.PI);
			} else if ((Math
					.pow((_role1P.getLayoutX() + _role1P.getFitWidth() / 2)
							- (_boss.getLayoutX() + _boss.getFitWidth() / 2), 2)
					+ Math.pow(
							(_role1P.getLayoutY() + _role1P.getFitHeight() / 2)
									- (_boss.getLayoutY() + _boss.getFitHeight() / 2),
							2) >= Math
									.pow((_role2P.getLayoutX() + _role2P.getFitWidth() / 2)
											- (_boss.getLayoutX() + _boss.getFitWidth() / 2), 2)
									+ Math.pow((_role2P.getLayoutY() + _role2P.getFitHeight() / 2)
											- (_boss.getLayoutY() + _boss.getFitHeight() / 2), 2)
					&& _bloodNum2P > 0) || _bloodNum1P <= 0) {
				newLaser.setRotate(Math.atan2(
						(_role2P.getLayoutY() + _role2P.getFitHeight() / 2)
								- (newLaser.getLayoutY() + newLaser.getFitHeight() / 2),
						(_role2P.getLayoutX() + _role2P.getFitWidth() / 2)
								- (newLaser.getLayoutX() + newLaser.getFitWidth() / 2))
						* 180 / Math.PI);
			}
			Media media = new Media(getClass().getResource("../resource/laser.mp3").toString());
			bossLaser = new MediaPlayer(media);
			bossLaser.play();
			_lasers.push(newLaser);
			_field.getChildren().add(newLaser);
		});
		fpsLaser.getKeyFrames().addAll(KLaser);
		fpsLaser.setCycleCount(Timeline.INDEFINITE);
		fpsLaser.play();
		Timeline fpsBullet = new Timeline(new KeyFrame(Duration.millis(1), (e) -> { // generate a bullet
			if (j == true) {
				ImageView newBullet = new ImageView();
				if (shootCount1P == 0) {
					newBullet.setImage(_weapon1P.getImage());
					newBullet.setFitWidth(_weapon1P.getFitWidth());
					newBullet.setFitHeight(_weapon1P.getFitHeight());
					newBullet.setLayoutX(_role1P.getLayoutX() + _role1P.getFitWidth() / 2);
					newBullet.setLayoutY(
							_role1P.getLayoutY() + _role1P.getFitHeight() / 2 - _weapon1P.getFitHeight() / 2);
					newBullet.setPreserveRatio(true);
					newBullet.setRotationAxis(Rotate.Y_AXIS);
					newBullet.setRotate(_role1P.getRotate());
					if (_role1P.getRotate() == 180)
						_bulletsA.push(newBullet);
					else if (_role1P.getRotate() == 0)
						_bulletsD.push(newBullet);
					_field.getChildren().add(newBullet);
				}
				shootCount1P++;
				if (shootCount1P == 400) {
					j = false;
					shootCount1P = 0;
				}
			}
			if (num1 == true) {
				ImageView newBullet = new ImageView();
				if (shootCount2P == 0) {
					newBullet.setImage(_weapon2P.getImage());
					newBullet.setFitWidth(_weapon2P.getFitWidth());
					newBullet.setFitHeight(_weapon2P.getFitHeight());
					newBullet.setLayoutX(_role2P.getLayoutX() + _role2P.getFitWidth() / 2);
					newBullet.setLayoutY(
							_role2P.getLayoutY() + _role2P.getFitHeight() / 2 - _weapon2P.getFitHeight() / 2);
					newBullet.setPreserveRatio(true);
					newBullet.setRotationAxis(Rotate.Y_AXIS);
					newBullet.setRotate(_role2P.getRotate());
					if (_role2P.getRotate() == 180)
						_bulletsLeft.push(newBullet);
					if (_role2P.getRotate() == 0)
						_bulletsRight.push(newBullet);
					_field.getChildren().add(newBullet);
				}
				shootCount2P++;
				if (shootCount2P == 400) {
					num1 = false;
					shootCount2P = 0;
				}
			}
		}));
		fpsBullet.setCycleCount(Timeline.INDEFINITE);
		fpsBullet.play();
		Timeline fpsMove = new Timeline(new KeyFrame(Duration.millis(1), (e) -> { // move
			if (_boss.getLayoutX() <= 660) {
				bossMove = 2;
				_boss.setRotate(180);
			} else if (_boss.getLayoutX() + _boss.getFitWidth() >= 860) {
				bossMove = 1;
				_boss.setRotate(0);
			}
			if (bossMove == 1)
				_boss.setLayoutX(_boss.getLayoutX() - 0.1);
			else if (bossMove == 2)
				_boss.setLayoutX(_boss.getLayoutX() + 0.1);
			if (a == true) {
				if (_role1P.getLayoutX() > 0)
					_role1P.setLayoutX(_role1P.getLayoutX() - 0.2);
				_role1P.setRotate(180);
			}
			if (d == true) {
				if (_role1P.getLayoutX() < _scene.getFitWidth() - _role1P.getFitWidth())
					_role1P.setLayoutX(_role1P.getLayoutX() + 0.2);
				_role1P.setRotate(0);
			}
			if (left == true) {
				if (_role2P.getLayoutX() > 0)
					_role2P.setLayoutX(_role2P.getLayoutX() - 0.2);
				_role2P.setRotate(180);
			}
			if (right == true) {
				if (_role2P.getLayoutX() < _scene.getFitWidth() - _role2P.getFitWidth())
					_role2P.setLayoutX(_role2P.getLayoutX() + 0.2);
				_role2P.setRotate(0);
			}
		}));
		fpsMove.setCycleCount(Timeline.INDEFINITE);
		fpsMove.play();
		Timeline fpsJump = new Timeline(new KeyFrame(Duration.millis(10), (event) -> { // jump
			if (_role1P.getLayoutY() + _role1P.getFitHeight() < 848
					&& !(_role1P.getLayoutY() + _role1P.getFitHeight() > 680
							&& _role1P.getLayoutY() + _role1P.getFitHeight() < 700
							&& ((_role1P.getLayoutX() + _role1P.getFitWidth() > 440 && _role1P.getLayoutX() < 640)
									|| (_role1P.getLayoutX() + _role1P.getFitWidth() > 880
											&& _role1P.getLayoutX() < 1080)))
					&& !(_role1P.getLayoutY() + _role1P.getFitHeight() > 520
							&& _role1P.getLayoutY() + _role1P.getFitHeight() < 540
							&& ((_role1P.getLayoutX() + _role1P.getFitWidth() > 220 && _role1P.getLayoutX() < 420)
									|| (_role1P.getLayoutX() + _role1P.getFitWidth() > 660
											&& _role1P.getLayoutX() < 860)
									|| (_role1P.getLayoutX() + _role1P.getFitWidth() > 1100
											&& _role1P.getLayoutX() < 1300)))) {
				_role1P.setLayoutY(_role1P.getLayoutY() + 0.004 * 9.8 / 2 * Math.pow(downCount1P, 1.5));
				downCount1P++;
			} else
				downCount1P = 0;
			if (w == true) {
				if (jumpCount1P < 50) {
//					if (!(_role1P.getLayoutY() > 680 && _role1P.getLayoutY() < 700
//							&& ((_role1P.getLayoutX() + _role1P.getFitWidth() > 440 && _role1P.getLayoutX() < 640)
//									|| (_role1P.getLayoutX() + _role1P.getFitWidth() > 880
//											&& _role1P.getLayoutX() < 1080)))
//							&& !(_role1P.getLayoutY() > 520 && _role1P.getLayoutY() < 540
//									&& ((_role1P.getLayoutX() + _role1P.getFitWidth() > 220
//											&& _role1P.getLayoutX() < 420)
//											|| (_role1P.getLayoutX() + _role1P.getFitWidth() > 660
//													&& _role1P.getLayoutX() < 860)
//											|| (_role1P.getLayoutX() + _role1P.getFitWidth() > 1100
//													&& _role1P.getLayoutX() < 1300))))
					_role1P.setLayoutY(_role1P.getLayoutY() - 0.008 * 9.8 / 2 * Math.pow(50 - jumpCount1P, 1.5));
//					else
//						jumpCount1P = 50;
				} else {
					if (downCount1P == 0) {
						w = false;
						jumpCount1P = -1;
					}
				}
				jumpCount1P++;
			}
			if (_role2P.getLayoutY() + _role2P.getFitHeight() < 848
					&& !(_role2P.getLayoutY() + _role2P.getFitHeight() > 680
							&& _role2P.getLayoutY() + _role2P.getFitHeight() < 700
							&& ((_role2P.getLayoutX() + _role2P.getFitWidth() > 440 && _role2P.getLayoutX() < 640)
									|| (_role2P.getLayoutX() + _role2P.getFitWidth() > 880
											&& _role2P.getLayoutX() < 1080)))
					&& !(_role2P.getLayoutY() + _role2P.getFitHeight() > 520
							&& _role2P.getLayoutY() + _role2P.getFitHeight() < 540
							&& ((_role2P.getLayoutX() + _role2P.getFitWidth() > 220 && _role2P.getLayoutX() < 420)
									|| (_role2P.getLayoutX() + _role2P.getFitWidth() > 660
											&& _role2P.getLayoutX() < 860)
									|| (_role2P.getLayoutX() + _role2P.getFitWidth() > 1100
											&& _role2P.getLayoutX() < 1300)))) {
				_role2P.setLayoutY(_role2P.getLayoutY() + 0.004 * 9.8 / 2 * Math.pow(downCount2P, 1.5));
				downCount2P++;
			} else
				downCount2P = 0;
			if (up == true) {
				if (jumpCount2P < 50) {
//					if (!(_role2P.getLayoutY() > 680 && _role2P.getLayoutY() < 700
//							&& ((_role2P.getLayoutX() + _role2P.getFitWidth() > 440 && _role2P.getLayoutX() < 640)
//									|| (_role2P.getLayoutX() + _role2P.getFitWidth() > 880
//											&& _role2P.getLayoutX() < 1080)))
//							&& !(_role2P.getLayoutY() > 520 && _role2P.getLayoutY() < 540
//									&& ((_role2P.getLayoutX() + _role2P.getFitWidth() > 220
//											&& _role2P.getLayoutX() < 420)
//											|| (_role2P.getLayoutX() + _role2P.getFitWidth() > 660
//													&& _role2P.getLayoutX() < 860)
//											|| (_role2P.getLayoutX() + _role2P.getFitWidth() > 1100
//													&& _role2P.getLayoutX() < 1300))))
					_role2P.setLayoutY(_role2P.getLayoutY() - 0.008 * 9.8 / 2 * Math.pow(50 - jumpCount2P, 1.5));
//					else
//						jumpCount2P = 50;
				} else {
					if (downCount2P == 0) {
						up = false;
						jumpCount2P = -1;
					}
				}
				jumpCount2P++;
			}
		}));
		fpsJump.setCycleCount(Timeline.INDEFINITE);
		fpsJump.play();
	}
}
