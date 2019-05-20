/*
 * Depends:
 *  om-grid.js
 */

(function($) {

    /**
     * 行拖拽
     */
    $.omWidget.addInitListener('om.omGrid', function() {
	var self = this, $elem = self.element, ops = self.options;

	this.tbody.delegate('tr.om-grid-row', 'mousedown', function(event) {
	    if (!$(this).hasClass("om-draggable")) {
		$(this).omDraggable( { helper : "clone", containment : 'parent', revert : "invalid", axis : "y",
		    onDrag : function(ui, event) {

			if (ui.position.top - ui.originalPosition.top > 0 && $(this).next().length > 0) {// 向下拖动
			if ((ui.offset.top - $(this).next().offset().top) >= -$(this).next().height() / 2) {
			    $(this).next().after($(this));
			    $(this).omDraggable("original", { X : ui.originalPosition.left, Y : $(this).position().top
			    })
			}
		    } else if ($(this).prev().length > 0) {// 向上拖动

			if (ui.offset.top - $(this).prev().offset().top <= $(this).next().height() / 2) {
			    $(this).prev().before($(this));
			    $(this).omDraggable("original", { X : ui.originalPosition.left, Y : $(this).position().top
			    })
			}
		    } else {

		    }
		}, onStop : function() {
		    // 拖拽后需要重置序列号和grid样式
		    self.resetGrid();
		}
		});

	    } else {

	    }

	});

	$.extend(this, {

	resetGrid : function() {// 重置序列号和grid样式

	}
	})
    })
})(jQuery);
