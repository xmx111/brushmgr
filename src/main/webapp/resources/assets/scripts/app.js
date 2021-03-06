var App = function () {
  var B = false;
  var H = false;
  var G = false;
  var h = false;
  var f = 225;
  var l = 35;
  var q = [
  ];
  var e = {
    blue: '#4b8df8',
    red: '#e02222',
    green: '#35aa47',
    purple: '#852b99',
    grey: '#555555',
    'light-grey': '#fafafa',
    yellow: '#ffb848'
  };
  var z = function () {
    var M = window,
    L = 'inner';
    if (!('innerWidth' in window)) {
      L = 'client';
      M = document.documentElement || document.body
    }
    return {
      width: M[L + 'Width'],
      height: M[L + 'Height']
    }
  };
  var w = function () {
    if ($('body') .css('direction') === 'rtl') {
      B = true
    }
    H = !!navigator.userAgent.match(/MSIE 8.0/);
    G = !!navigator.userAgent.match(/MSIE 9.0/);
    h = !!navigator.userAgent.match(/MSIE 10.0/);
    if (h) {
      jQuery('html') .addClass('ie10')
    }
    if (h || G || H) {
      jQuery('html') .addClass('ie')
    }
    var L = navigator.userAgent.toLowerCase();
    if (L.match(/(iphone|ipod|ipad)/)) {
      $(document) .on('focus', 'input, textarea', function () {
        $('.header') .hide();
        $('.footer') .hide()
      });
      $(document) .on('blur', 'input, textarea', function () {
        $('.header') .show();
        $('.footer') .show()
      })
    }
  };
  var m = function () {
    var L = z();
    if (L.width < 992) {
      $('body') .removeClass('page-sidebar-closed')
    }
  };
  var y = function () {
    for (var L in q) {
      var M = q[L];
      M.call()
    }
  };
  var F = function () {
    m();
    d();
    c();
    y()
  };
  var E = function () {
    m();
    d()
  };
  var s = function () {
    var L;
    if (H) {
      var M;
      $(window) .resize(function () {
        if (M == document.documentElement.clientHeight) {
          return
        }
        if (L) {
          clearTimeout(L)
        }
        L = setTimeout(function () {
          F()
        }, 50);
        M = document.documentElement.clientHeight
      })
    } else {
      $(window) .resize(function () {
        if (L) {
          clearTimeout(L)
        }
        L = setTimeout(function () {
          F()
        }, 50)
      })
    }
  };
  var d = function () {
    var N = $('.page-content');
    var O = $('.page-sidebar');
    var M = $('body');
    var L;
    if (M.hasClass('page-footer-fixed') === true && M.hasClass('page-sidebar-fixed') === false) {
      var P = $(window) .height() - $('.footer') .outerHeight();
      if (N.height() < P) {
        N.attr('style', 'min-height:' + P + 'px !important')
      }
    } else {
      if (M.hasClass('page-sidebar-fixed')) {
        L = g()
      } else {
        L = O.height() + 20
      }
      if (L >= N.height()) {
        N.attr('style', 'min-height:' + L + 'px !important')
      }
    }
  };
  var K = function () {
    jQuery('.page-sidebar') .on('click', 'li > a', function (Q) {
      if ($(this) .next() .hasClass('sub-menu') == false) {
        if ($('.btn-navbar') .hasClass('collapsed') == false) {
          $('.btn-navbar') .click()
        }
        return
      }
      if ($(this) .next() .hasClass('sub-menu.always-open')) {
        return
      }
      var P = $(this) .parent() .parent();
      var O = $(this);
      var N = jQuery(this) .next();
      var L = - 200;
      var M = 200;
      if (N.is(':visible')) {
        jQuery('.arrow', jQuery(this)) .removeClass('open');
        jQuery(this) .parent() .removeClass('open');
        N.slideUp(M, function () {
          d()
        })
      } else {
        jQuery('.arrow', jQuery(this)) .addClass('open');
        jQuery(this) .parent() .addClass('open');
        N.slideDown(M, function () {
          d()
        })
      }
      Q.preventDefault()
    })
  };
  var g = function () {
    var L = $(window) .height() - $('.header') .height() + 1;
    if ($('body') .hasClass('page-footer-fixed')) {
      L = L - $('.footer') .outerHeight()
    }
    return L
  };
  var c = function () {
    var N = $('.page-sidebar-menu');
    if (N.parent('.slimScrollDiv') .size() === 1) {
      N.slimScroll({
        destroy: true
      });
      N.removeAttr('style');
      $('.page-sidebar') .removeAttr('style')
    }
    if ($('.page-sidebar-fixed') .size() === 0) {
      d();
      return
    }
    var L = z();
    if (L.width >= 992) {
      var M = g();
      N.slimScroll({
        size: '7px',
        color: '#a1b2bd',
        opacity: 0.3,
        position: B ? 'left' : 'right',
        height: M,
        allowPageScroll: false,
        disableFadeOut: false
      });
      d()
    }
  };
  var D = function () {
    if ($('body') .hasClass('page-sidebar-fixed') === false) {
      return
    }
    $('.page-sidebar') .off('mouseenter') .on('mouseenter', function () {
      var L = $('body');
      if ((L.hasClass('page-sidebar-closed') === false || L.hasClass('page-sidebar-fixed') === false) || $(this) .hasClass('page-sidebar-hovering')) {
        return
      }
      L.removeClass('page-sidebar-closed') .addClass('page-sidebar-hover-on');
      $(this) .addClass('page-sidebar-hovering');
      $(this) .animate({
        width: f
      }, 400, '', function () {
        $(this) .removeClass('page-sidebar-hovering')
      })
    });
    $('.page-sidebar') .off('mouseleave') .on('mouseleave', function () {
      var L = $('body');
      if ((L.hasClass('page-sidebar-hover-on') === false || L.hasClass('page-sidebar-fixed') === false) || $(this) .hasClass('page-sidebar-hovering')) {
        return
      }
      $(this) .addClass('page-sidebar-hovering');
      $(this) .animate({
        width: l
      }, 400, '', function () {
        $('body') .addClass('page-sidebar-closed') .removeClass('page-sidebar-hover-on');
        $(this) .removeClass('page-sidebar-hovering')
      })
    })
  };
  var v = function () {
    var L = z();
    if ($.cookie('sidebar_closed') === '1' && L.width >= 992) {
      $('body') .addClass('page-sidebar-closed')
    }
    $('.page-sidebar, .header') .on('click', '.sidebar-toggler', function (O) {
      var M = $('body');
      var N = $('.page-sidebar');
      if ((M.hasClass('page-sidebar-hover-on') && M.hasClass('page-sidebar-fixed')) || N.hasClass('page-sidebar-hovering')) {
        M.removeClass('page-sidebar-hover-on');
        N.css('width', '') .hide() .show();
        $.cookie('sidebar_closed', '0');
        O.stopPropagation();
        y();
        return
      }
      $('.sidebar-search', N) .removeClass('open');
      if (M.hasClass('page-sidebar-closed')) {
        M.removeClass('page-sidebar-closed');
        if (M.hasClass('page-sidebar-fixed')) {
          N.css('width', '')
        }
        $.cookie('sidebar_closed', '0')
      } else {
        M.addClass('page-sidebar-closed');
        $.cookie('sidebar_closed', '1')
      }
      y()
    });
    $('.page-sidebar') .on('click', '.sidebar-search .remove', function (M) {
      M.preventDefault();
      $('.sidebar-search') .removeClass('open')
    });
    $('.page-sidebar') .on('keypress', '.sidebar-search input', function (M) {
      if (M.which == 13) {
        $('.sidebar-search') .submit();
        return false
      }
    });
    $('.sidebar-search .submit') .on('click', function (M) {
      M.preventDefault();
      if ($('body') .hasClass('page-sidebar-closed')) {
        if ($('.sidebar-search') .hasClass('open') == false) {
          if ($('.page-sidebar-fixed') .size() === 1) {
            $('.page-sidebar .sidebar-toggler') .click()
          }
          $('.sidebar-search') .addClass('open')
        } else {
          $('.sidebar-search') .submit()
        }
      } else {
        $('.sidebar-search') .submit()
      }
    })
  };
  var n = function () {
    $('.header') .on('click', '.hor-menu .hor-menu-search-form-toggler', function (L) {
      if ($(this) .hasClass('off')) {
        $(this) .removeClass('off');
        $('.header .hor-menu .search-form') .hide()
      } else {
        $(this) .addClass('off');
        $('.header .hor-menu .search-form') .show()
      }
      L.preventDefault()
    });
    $('.header') .on('click', '.hor-menu .search-form .btn', function (L) {
      $('.form-search') .submit();
      L.preventDefault()
    });
    $('.header') .on('keypress', '.hor-menu .search-form input', function (L) {
      if (L.which == 13) {
        $('.form-search') .submit();
        return false
      }
    })
  };
  var x = function () {
    jQuery('.footer') .on('click', '.go-top', function (L) {
      App.scrollTo();
      L.preventDefault()
    })
  };
  var J = function () {
    jQuery('body') .on('click', '.portlet > .portlet-title > .tools > a.remove', function (L) {
      L.preventDefault();
      jQuery(this) .closest('.portlet') .remove()
    });
    jQuery('body') .on('click', '.portlet > .portlet-title > .tools > .collapse, .portlet .portlet-title > .tools > .expand', function (M) {
      M.preventDefault();
      var L = jQuery(this) .closest('.portlet') .children('.portlet-body');
      if (jQuery(this) .hasClass('collapse')) {
        jQuery(this) .removeClass('collapse') .addClass('expand');
        L.slideUp(200)
      } else {
        jQuery(this) .removeClass('expand') .addClass('collapse');
        L.slideDown(200)
      }
    })
  };
  var i = function () {
    var L;
    jQuery('body') .on('click', '.accordion.scrollable .accordion-toggle', function () {
      L = jQuery(this)
    });
    jQuery('body') .on('show.bs.collapse', '.accordion.scrollable', function () {
      jQuery('html,body') .animate({
        scrollTop: L.offset() .top - 150
      }, 'slow')
    })
  };
  var I = function () {
    $('body') .on('shown.bs.tab', '.nav.nav-tabs', function () {
      d()
    });
    if (location.hash) {
      var L = location.hash.substr(1);
      $('a[href="#' + L + '"]') .parents('.tab-pane:hidden') .each(function () {
        var M = $(this) .attr('id');
        $('a[href="#' + M + '"]') .click()
      });
      $('a[href="#' + L + '"]') .click()
    }
  };
  var k = function () {
    $('body') .on('hide.bs.modal', function () {
      if ($('.modal:visible') .size() > 1 && $('html') .hasClass('modal-open') == false) {
        $('html') .addClass('modal-open')
      } else {
        if ($('.modal:visible') .size() <= 1) {
          $('html') .removeClass('modal-open')
        }
      }
    });
    $('body') .on('show.bs.modal', '.modal', function () {
      if ($(this) .hasClass('modal-scroll')) {
        $('body') .addClass('modal-open-noscroll')
      }
    });
    $('body') .on('hide.bs.modal', '.modal', function () {
      $('body') .removeClass('modal-open-noscroll')
    })
  };
  var r = function () {
    jQuery('.tooltips') .tooltip()
  };
  var C = function () {
    if (App.isTouchDevice()) {
      $('[data-hover="dropdown"]') .each(function () {
        $(this) .parent() .off('hover');
        $(this) .off('hover')
      })
    }
    $('body') .on('click', '.dropdown-menu.hold-on-click', function (L) {
      L.stopPropagation()
    })
  };
  var j = function () {
    $('[data-hover="dropdown"]') .dropdownHover()
  };
  var A = function () {
    $('body') .on('click', '[data-close="alert"]', function (L) {
      $(this) .parent('.alert') .hide();
      L.preventDefault()
    })
  };
  var u;
  var t = function () {
    jQuery('.popovers') .popover();
    $(document) .on('click.bs.popover.data-api', function (L) {
      if (u) {
        u.popover('hide')
      }
    })
  };
  var o = function () {
    if (!jQuery.fancybox) {
      return
    }
    if (jQuery('.fancybox-button') .size() > 0) {
      jQuery('.fancybox-button') .fancybox({
        groupAttr: 'data-rel',
        prevEffect: 'none',
        nextEffect: 'none',
        closeBtn: true,
        helpers: {
          title: {
            type: 'inside'
          }
        }
      })
    }
  };
  var p = function () {
    if (H || G) {
      jQuery('input[placeholder]:not(.placeholder-no-fix), textarea[placeholder]:not(.placeholder-no-fix)') .each(function () {
        var L = jQuery(this);
        if (L.val() == '' && L.attr('placeholder') != '') {
          L.addClass('placeholder') .val(L.attr('placeholder'))
        }
        L.focus(function () {
          if (L.val() == L.attr('placeholder')) {
            L.val('')
          }
        });
        L.blur(function () {
          if (L.val() == '' || L.val() == L.attr('placeholder')) {
            L.val(L.attr('placeholder'))
          }
        })
      })
    }
  };
  var b = function () {
    function L() {
      if (!document.fullscreenElement && !document.mozFullScreenElement && !document.webkitFullscreenElement) {
        if (document.documentElement.requestFullscreen) {
          document.documentElement.requestFullscreen()
        } else {
          if (document.documentElement.mozRequestFullScreen) {
            document.documentElement.mozRequestFullScreen()
          } else {
            if (document.documentElement.webkitRequestFullscreen) {
              document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT)
            }
          }
        }
      } else {
        if (document.cancelFullScreen) {
          document.cancelFullScreen()
        } else {
          if (document.mozCancelFullScreen) {
            document.mozCancelFullScreen()
          } else {
            if (document.webkitCancelFullScreen) {
              document.webkitCancelFullScreen()
            }
          }
        }
      }
    }
    $('#trigger_fullscreen') .click(function () {
      L()
    })
  };
  var a = function () {
    var L = $('.theme-panel');
    if ($('body') .hasClass('page-boxed') == false) {
      $('.layout-option', L) .val('fluid')
    }
    $('.sidebar-option', L) .val('default');
    $('.header-option', L) .val('fixed');
    $('.footer-option', L) .val('default');
    var N = function () {
      $('body') .removeClass('page-boxed') .removeClass('page-footer-fixed') .removeClass('page-sidebar-fixed') .removeClass('page-header-fixed');
      $('.header > .header-inner') .removeClass('container');
      if ($('.page-container') .parent('.container') .size() === 1) {
        $('.page-container') .insertAfter('body > .clearfix')
      }
      if ($('.footer > .container') .size() === 1) {
        $('.footer') .html($('.footer > .container') .html())
      } else {
        if ($('.footer') .parent('.container') .size() === 1) {
          $('.footer') .insertAfter('.page-container')
        }
      }
      $('body > .container') .remove()
    };
    var P = '';
    var O = function () {
      var U = $('.layout-option', L) .val();
      var S = $('.sidebar-option', L) .val();
      var R = $('.header-option', L) .val();
      var T = $('.footer-option', L) .val();
      if (S == 'fixed' && R == 'default') {
        alert('Default Header with Fixed Sidebar option is not supported. Proceed with Fixed Header with Fixed Sidebar.');
        $('.header-option', L) .val('fixed');
        $('.sidebar-option', L) .val('fixed');
        S = 'fixed';
        R = 'fixed'
      }
      N();
      if (U === 'boxed') {
        $('body') .addClass('page-boxed');
        $('.header > .header-inner') .addClass('container');
        var Q = $('body > .clearfix') .after('<div class="container"></div>');
        $('.page-container') .appendTo('body > .container');
        if (T === 'fixed') {
          $('.footer') .html('<div class="container">' + $('.footer') .html() + '</div>')
        } else {
          $('.footer') .appendTo('body > .container')
        }
      }
      if (P != U) {
        y()
      }
      P = U;
      if (R === 'fixed') {
        $('body') .addClass('page-header-fixed');
        $('.header') .removeClass('navbar-static-top') .addClass('navbar-fixed-top')
      } else {
        $('body') .removeClass('page-header-fixed');
        $('.header') .removeClass('navbar-fixed-top') .addClass('navbar-static-top')
      }
      if (S === 'fixed') {
        $('body') .addClass('page-sidebar-fixed')
      } else {
        $('body') .removeClass('page-sidebar-fixed')
      }
      if (T === 'fixed') {
        $('body') .addClass('page-footer-fixed')
      } else {
        $('body') .removeClass('page-footer-fixed')
      }
      d();
      c();
      D()
    };
    var M = function (Q) {
      $('#style_color') .attr('href', WEB_ROOT+'/resources/assets/css/themes/' + Q + '.css');
      $.cookie('style_color', Q)
    };
    $('.toggler', L) .click(function () {
      $('.toggler') .hide();
      $('.toggler-close') .show();
      $('.theme-panel > .theme-options') .show()
    });
    $('.toggler-close', L) .click(function () {
      $('.toggler') .show();
      $('.toggler-close') .hide();
      $('.theme-panel > .theme-options') .hide()
    });
    $('.theme-colors > ul > li', L) .click(function () {
      var Q = $(this) .attr('data-style');
      M(Q);
      $('ul > li', L) .removeClass('current');
      $(this) .addClass('current')
    });
    $('.layout-option, .header-option, .sidebar-option, .footer-option', L) .change(O);
    if ($.cookie('style_color')) {
      M($.cookie('style_color'))
    }
  };
  return {
    init: function () {
      w();
      s();
      E();
      c();
      D();
      K();
      n();
      v();
      p();
      x();
      a();
      b();
      o();
      J();
      A();
      C();
      I();
      r();
      t();
      i();
      k()
    },
    initAjax: function () {
      handleSelect2();
      C();
      r();
      t();
      i();
      j()
    },
    fixContentHeight: function () {
      d()
    },
    setLastPopedPopover: function (L) {
      u = L
    },
    addResponsiveHandler: function (L) {
      q.push(L)
    },
    setEqualHeight: function (L) {
      var M = 0;
      L = jQuery(L);
      L.each(function () {
        var N = $(this) .height();
        if (N > M) {
          tallestColumn = N
        }
      });
      L.height(M)
    },
    scrollTo: function (M, L) {
      pos = (M && M.size() > 0) ? M.offset() .top : 0;
      jQuery('html,body') .animate({
        scrollTop: pos + (L ? L : 0)
      }, 'slow')
    },
    scrollTop: function () {
      App.scrollTo()
    },
    blockUI: function (L, M) {
      var L = jQuery(L);
      if (L.height() <= 400) {
        M = true
      }
      L.block({
        message: '<img src="' + WEB_ROOT + '/resources/assets/img/ajax-loading.gif" align="">',
        centerY: M != undefined ? M : true,
        css: {
          top: '10%',
          border: 'none',
          padding: '2px',
          backgroundColor: 'none'
        },
        overlayCSS: {
          backgroundColor: '#000',
          opacity: 0.05,
          cursor: 'wait'
        }
      })
    },
    unblockUI: function (M, L) {
      jQuery(M) .unblock({
        onUnblock: function () {
          jQuery(M) .css('position', '');
          jQuery(M) .css('zoom', '')
        }
      })
    },
    initUniform: function (L) {
      if (L) {
        jQuery(L) .each(function () {
          if ($(this) .parents('.checker') .size() == 0) {
            $(this) .show();
            $(this) .uniform()
          }
        })
      } else {
        handleUniform()
      }
    },
    updateUniform: function (L) {
      $.uniform.update(L)
    },
    initFancybox: function () {
      o()
    },
    getActualVal: function (L) {
      var L = jQuery(L);
      if (L.val() === L.attr('placeholder')) {
        return ''
      }
      return L.val()
    },
    getURLParameter: function (N) {
      var L = window.location.search.substring(1),
      M,
      P,
      O = L.split('&');
      for (M = 0; M < O.length; M++) {
        P = O[M].split('=');
        if (P[0] == N) {
          return unescape(P[1])
        }
      }
      return null
    },
    isTouchDevice: function () {
      try {
        document.createEvent('TouchEvent');
        return true
      } catch (L) {
        return false
      }
    },
    isIE8: function () {
      return H
    },
    isIE9: function () {
      return G
    },
    isRTL: function () {
      return B
    },
    getLayoutColorCode: function (L) {
      if (e[L]) {
        return e[L]
      } else {
        return ''
      }
    }
  }
}();
